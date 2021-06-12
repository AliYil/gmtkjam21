package com.aliyil.gmtkjam21.entity.core;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.Utilities;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class LoadingBar extends GameObject {
    private Rectangle rect;

    public LoadingBar(Game game) {
        super(game);
        float width = 400;
        float height = 20;
//        rect = new Rectangle(Game.w * 0.5f - width / 2, Game.h * 0.3f - height / 2, width, height);
        rect = new Rectangle(0 - width / 2, -200 - height / 2, width, height);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void shapeRender(ShapeRenderer shapeRenderer) {
        Utilities.drawRectLine(shapeRenderer, Utilities.enlargeRectangle(rect, 20), 10);
        shapeRenderer.rect(rect.getX(), rect.getY(), rect.getWidth() * getGameInstance().getResourceManager().getProgress(), rect.getHeight());
        super.shapeRender(shapeRenderer);
    }
}
