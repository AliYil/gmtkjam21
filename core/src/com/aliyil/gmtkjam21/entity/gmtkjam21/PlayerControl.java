package com.aliyil.gmtkjam21.entity.gmtkjam21;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.core.GameObject;
import com.aliyil.gmtkjam21.entity.gmtkjam21.screen.Level;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class PlayerControl extends GameObject {
    private Character master;
    private Character slave;

    public PlayerControl(Game game) {
        super(game);
        enableInputListener(0);
    }

    @Override
    public void start() {
        super.start();
    }

    public boolean isCharactersAligned = false;
    public boolean characterAlignmentIndicatorEnabled = true;

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.UP:
            case Input.Keys.W:
                move(0);
                break;
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                move(1);
                break;
            case Input.Keys.DOWN:
            case Input.Keys.S:
                move(2);
                break;
            case Input.Keys.LEFT:
            case Input.Keys.A:
                move(3);
                break;
            case Input.Keys.R:
                Restart restart = new Restart(getGameInstance());
                restart.start();
                stop();
                break;
        }
        return super.keyDown(keycode);
    }

    private int reverseDirection(int direction){
        switch (direction){
            case 0: return 2;
            case 1: return 3;
            case 2: return 0;
            case 3: return 1;
//            case 10: return 12;
//            case 11: return 13;
//            case 12: return 10;
//            case 13: return 11;
        }
        throw new RuntimeException("Invalid direction");
    }

    private Vector2 getDirectionVector(int direction){
        switch (direction){
            case 0: return new Vector2(0, 1);
            case 1: return new Vector2(1, 0);
            case 2: return new Vector2(0, -1);
            case 3: return new Vector2(-1, 0);
        }
        throw new RuntimeException("Invalid direction");
    }

    private void move(int direction){
        if(master.canMove() && slave.canMove()){
            Vector2 masterNewGridPos = GameObjectGrid.toGrid(master.getPosVector()).add(getDirectionVector(direction));
            Vector2 slaveNewGridPos = GameObjectGrid.toGrid(slave.getPosVector()).add(getDirectionVector(direction).scl(-1));

            boolean masterMoved = false;
            boolean slaveMoved = false;

            if(!isObstacle(masterNewGridPos)){
                master.move(direction);
                masterMoved = true;
                if(!isObstacle(slaveNewGridPos)){
                    slave.move(reverseDirection(direction));
                    slaveMoved = true;
                }else{
                    slave.move(reverseDirection(direction)+10);
                }
            }else{
                master.move(direction+10);
//                slave.move(reverseDirection(direction)+10);
            }

            if(masterNewGridPos.epsilonEquals(slaveNewGridPos, .1f)){
                if(getGrid().testObject(Goal.class, masterNewGridPos)){
                    master.move(direction);
                    slave.move(reverseDirection(direction));
                    masterMoved = true;
                    slaveMoved = true;

                    getGameInstance().getParticleEffectManager().newStars(GameObjectGrid.toWorld(masterNewGridPos));

                    Win win = new Win(getGameInstance()){
                        @Override
                        public void onComplete() {
                            getLevel().toNextLevel();
                        }
                    };
                    win.start();

                    stop();
                }else{
                    master.move(direction+10);
                    slave.move(reverseDirection(direction)+10);

                    masterMoved = false;
                    slaveMoved = false;
                }
            }

            if(!masterMoved) masterNewGridPos = GameObjectGrid.toGrid(master.getPosVector());
            if(!slaveMoved) slaveNewGridPos = GameObjectGrid.toGrid(slave.getPosVector());

            if(masterMoved || slaveMoved){
                getGameInstance().getSoundManager().jump();

                if(characterAlignmentIndicatorEnabled){
                    Goal goal = (Goal)getGameInstance().getEntityOrNull(Goal.class);
                    Vector2 goalGridPos = GameObjectGrid.toGrid(goal.getPosVector());
                    checkForCharacterAlignment(masterNewGridPos, slaveNewGridPos, goalGridPos);
                }
            }else{
                getGameInstance().getSoundManager().jump2();
            }
        }
    }

    public void checkForCharacterAlignment(Vector2 masterNewGridPos, Vector2 slaveNewGridPos, Vector2 goalGridPos){
        if(masterNewGridPos.cpy().rotateAround(goalGridPos, 180).epsilonEquals(slaveNewGridPos, .1f)){
            if(!isCharactersAligned){
                getGameInstance().getSoundManager().beep();
            }
            isCharactersAligned = true;
        }else{
            isCharactersAligned = false;
        }
    }

    @Override
    public void shapeRender(ShapeRenderer shapeRenderer) {
        super.shapeRender(shapeRenderer);
        if(isCharactersAligned){
            shapeRenderer.setColor(.1f, 1f, .1f, .2f);

            shapeRenderer.rectLine(master.getPosVector().cpy().lerp(slave.getPosVector(), .25f), slave.getPosVector().cpy().lerp(master.getPosVector(), .25f), 5);
        }
    }

    private boolean isObstacle(Vector2 gridPos){
        GameObjectGrid grid = getGrid();
        return grid.testObject(Wall.class, gridPos)
                || grid.testObject(Goal.class, gridPos)
                ;
    }

    public void setMaster(Character master) {
        this.master = master;
    }

    public void setSlave(Character slave) {
        this.slave = slave;
    }

    private GameObjectGrid getGrid(){
        return getLevel().getTileGrid();
    }

    private Level getLevel(){
        return (Level)getGameInstance().getCurrentScreen();
    }


}
