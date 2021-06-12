package com.aliyil.gmtkjam21.entity.gmtkjam21;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.core.SpriteEntity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class TileBase extends SpriteEntity {
    public TileBase(Game game, Texture texture) {
        super(game, texture);

        setSize(GameObjectGrid.cellSize, GameObjectGrid.cellSize);
    }

    @Override
    public void shapeRender(ShapeRenderer shapeRenderer) {
        super.shapeRender(shapeRenderer);
        float cellSize = GameObjectGrid.cellSize;
        float cellSizeHalf = cellSize/2f;
        shapeRenderer.setColor(.1f, .1f, .1f, .06f);
        shapeRenderer.rect(getX()-cellSizeHalf, getY()-cellSizeHalf, 4, cellSize);
        shapeRenderer.rect(getX()-cellSizeHalf+4, getY()-cellSizeHalf, cellSize-4, 4);
    }
}
