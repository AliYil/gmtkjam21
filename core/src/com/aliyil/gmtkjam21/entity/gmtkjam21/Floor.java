package com.aliyil.gmtkjam21.entity.gmtkjam21;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.Utilities;

public class Floor extends TileBase {
    public Floor(Game game) {
        super(game, game.getResourceManager().grass1);

        if (Utilities.RANDOM.nextFloat() > 0.5f)
            getSprite().setTexture(game.getResourceManager().grass2);

        zIndex = 0;
    }
}
