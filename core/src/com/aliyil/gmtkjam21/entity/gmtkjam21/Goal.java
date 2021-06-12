package com.aliyil.gmtkjam21.entity.gmtkjam21;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.core.SpriteEntity;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Goal extends SpriteEntity {
    public Goal(Game game) {
        super(game, .2f, game.getResourceManager().goal.getRegions());
        setSize(GameObjectGrid.cellSize, GameObjectGrid.cellSize);
    }
}
