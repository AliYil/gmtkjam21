package com.aliyil.gmtkjam21.entity.gmtkjam21;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.core.Entity;
import com.aliyil.gmtkjam21.entity.gmtkjam21.screen.Level;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class PlayerControl extends Entity {
    private Character master;
    private Character slave;

    public PlayerControl(Game game) {
        super(game);
        enableInputListener(0);
    }

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
        }
        return super.keyDown(keycode);
    }

    private int reverseDirection(int direction){
        switch (direction){
            case 0: return 2;
            case 1: return 3;
            case 2: return 0;
            case 3: return 1;
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

            if(!isObstacle(masterNewGridPos)){
                master.move(direction);
                if(!isObstacle(slaveNewGridPos)){
                    slave.move(reverseDirection(direction));
                }
            }

            if(masterNewGridPos.epsilonEquals(slaveNewGridPos)){
                if(getGrid().testObject(Goal.class, masterNewGridPos)){
                    master.move(direction);
                    slave.move(reverseDirection(direction));

                    getLevel().toNextLevel();
                    stop();
                }else{
                    move(reverseDirection(direction));
                }
            }
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
