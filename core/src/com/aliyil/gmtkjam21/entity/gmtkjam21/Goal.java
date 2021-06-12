package com.aliyil.gmtkjam21.entity.gmtkjam21;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.core.GameObject;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Goal extends GameObject {
    public Goal(Game game) {
        super(game);
    }

    @Override
    public void shapeRender(ShapeRenderer shapeRenderer) {
        super.shapeRender(shapeRenderer);
        shapeRenderer.setColor(.6f, .2f, 0, 1f);
        shapeRenderer.rect(getX()-16, getY()-16, 32, 32);
    }
}
