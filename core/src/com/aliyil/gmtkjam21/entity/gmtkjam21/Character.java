package com.aliyil.gmtkjam21.entity.gmtkjam21;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.core.GameObject;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Character extends GameObject {
    public Character(Game game) {
        super(game);
    }

    @Override
    public void shapeRender(ShapeRenderer shapeRenderer) {
        super.shapeRender(shapeRenderer);
        shapeRenderer.rect(getX()-32, getY()-32, 64, 64);
    }

    public void move(int direction){
        switch (direction){
            case 0:
                translate(0, GameObjectGrid.cellSize);
                break;
            case 1:
                translate(GameObjectGrid.cellSize, 0);
                break;
            case 2:
                translate(0, -GameObjectGrid.cellSize);
                break;
            case 3:
                translate(-GameObjectGrid.cellSize, 0);
                break;
        }
    }
}
