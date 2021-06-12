package com.aliyil.gmtkjam21.entity.gmtkjam21;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.Utilities;

public class Pit extends TileBase {
    public Pit(Game game) {
        super(game, game.getResourceManager().pit);

        zIndex = 1;
        isOverlay = true;
    }
}
