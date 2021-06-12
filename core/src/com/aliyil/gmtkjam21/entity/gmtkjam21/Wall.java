package com.aliyil.gmtkjam21.entity.gmtkjam21;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.Utilities;
import com.aliyil.gmtkjam21.entity.core.GameObject;
import com.aliyil.gmtkjam21.entity.core.SpriteEntity;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Wall extends TileBase {
    public Wall(Game game) {
        super(game, game.getResourceManager().tree1);
        zIndex = 2;

        if (Utilities.RANDOM.nextFloat() > 0.5f)
            getSprite().setTexture(game.getResourceManager().tree2);

        isOverlay = true;
    }
}
