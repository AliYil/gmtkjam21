package com.aliyil.gmtkjam21.entity.gmtkjam21;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.core.GameObject;
import com.aliyil.gmtkjam21.entity.core.SpriteEntity;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Character extends SpriteEntity {
    public Character(Game game, Array<TextureAtlas.AtlasRegion> textures) {
        super(game, .5f, textures);
        setSize(GameObjectGrid.cellSize, GameObjectGrid.cellSize);
        enableMoving(true);
        zIndex = 30;
    }

    private float animation = 1;
    private Vector2 targetPos = null;
    private Vector2 originalPos = null;

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
            }
        }
    }

    public void move(int direction){
        getGameInstance().getSoundManager().jump();
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
            default:
                throw new RuntimeException("Invalid direction");
        }
    }

    public boolean canMove(){
        return animation == 1;
    }
}
