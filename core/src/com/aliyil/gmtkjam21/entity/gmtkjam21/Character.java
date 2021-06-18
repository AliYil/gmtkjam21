package com.aliyil.gmtkjam21.entity.gmtkjam21;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.core.GameObject;
import com.aliyil.gmtkjam21.entity.core.SpriteEntity;
import com.aliyil.gmtkjam21.entity.gmtkjam21.screen.Level;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Character extends SpriteEntity {
    private float animation = 1;
    private Vector2 targetPos = null;
    private Vector2 originalPos = null;

    private float fallAnimation = 0;
    private boolean isFalling = false;

    public Character(Game game, Array<TextureAtlas.AtlasRegion> textures) {
        super(game, .5f, textures);
        setSize(GameObjectGrid.cellSize, GameObjectGrid.cellSize);
        zIndex = 30;
    }

    @Override
    public void tick() {
        super.tick();
        if(animation < 1){
            animation += dts() * 7f;

            setPosition(originalPos.cpy().lerp(targetPos, animation).add(0, (float) Math.sin(animation*3)*30f));

            if(animation >= 1){
                animation = 1;
                setPosition(targetPos);
                targetPos = null;
                originalPos = null;
                checkForEffectTiles();
            }
        }

        if(isFalling){
            fallAnimation += dts()*2;
            if(fallAnimation > 1f){
                new Restart(getGameInstance()).start();
                stop();
            }else{
                getSprite().setScale(1-fallAnimation);
                translateY(-dts()*30);
            }
        }
    }

    private void checkForEffectTiles(){
        Vector2 gridPos = GameObjectGrid.toGrid(getPosVector());
        if(getGrid().testObject(Pit.class, gridPos)){
            if(!isFalling){
                isFalling = true;
                getGameInstance().getSoundManager().fall();
            }
        }
    }

    private GameObjectGrid getGrid(){
        return getLevel().getTileGrid();
    }

    private Level getLevel(){
        return (Level)getGameInstance().getCurrentScreen();
    }

    public void move(int direction){
        animation = 0f;
        switch (direction){
            case 0:
                originalPos = getPosVector();
                targetPos = getPosVector().cpy().add(0, GameObjectGrid.cellSize);
                break;
            case 1:
                originalPos = getPosVector();
                targetPos = getPosVector().cpy().add(GameObjectGrid.cellSize, 0);
                flipHorizontal = true;
                break;
            case 2:
                originalPos = getPosVector();
                targetPos = getPosVector().cpy().add(0, -GameObjectGrid.cellSize);
                break;
            case 3:
                originalPos = getPosVector();
                targetPos = getPosVector().cpy().add(-GameObjectGrid.cellSize, 0);
                flipHorizontal = false;
                break;
            case 10:
            case 12:
                originalPos = getPosVector();
                targetPos = getPosVector();
                break;
            case 11:
                originalPos = getPosVector();
                targetPos = getPosVector();
                flipHorizontal = true;
                break;
            case 13:
                originalPos = getPosVector();
                targetPos = getPosVector();
                flipHorizontal = false;
                break;
            default:
                throw new RuntimeException("Invalid direction");
        }

        targetPos = GameObjectGrid.toWorld(GameObjectGrid.toGrid(targetPos));
    }

    public boolean canMove(){
        return animation == 1 && !isFalling;
    }
}
