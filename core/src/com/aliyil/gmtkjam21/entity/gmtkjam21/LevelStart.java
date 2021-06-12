package com.aliyil.gmtkjam21.entity.gmtkjam21;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.core.Text;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class LevelStart extends Text {
    private float timer = 0;
    public LevelStart(Game game) {
        super(game, "");
        zIndex = 5;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void tick() {
        super.tick();
        timer += dts();

        if(timer > .3f){
            kill();
        }
    }

    @Override
    public void shapeRender(ShapeRenderer shapeRenderer) {
        super.shapeRender(shapeRenderer);
        shapeRenderer.setColor(getSharedValues().bgColor);
        shapeRenderer.circle(getGameInstance().getCamera().position.x, getGameInstance().getCamera().position.y, (.3f-timer)*(Game.w));
    }
}
