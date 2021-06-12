package com.aliyil.gmtkjam21.entity.gmtkjam21;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.Utilities;
import com.aliyil.gmtkjam21.entity.core.SpriteEntity;

public class Floor extends SpriteEntity {
    public Floor(Game game) {
        super(game, game.getResourceManager().grass1);

        if (Utilities.RANDOM.nextFloat() > 0.5f)
            getSprite().setTexture(game.getResourceManager().grass2);

        setSize(GameObjectGrid.cellSize, GameObjectGrid.cellSize);
        zIndex = 0;
    }
}
