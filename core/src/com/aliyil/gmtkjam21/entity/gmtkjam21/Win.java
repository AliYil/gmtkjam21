package com.aliyil.gmtkjam21.entity.gmtkjam21;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.core.Text;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Win extends Text {
    private float timer = 0;

    public Win(Game game) {
        super(game, "");
        zIndex = 5;
    }

    @Override
    public void start() {
        super.start();
        getGameInstance().getSoundManager().win();
    }

    @Override
    public void tick() {
        super.tick();
        timer += dts();

        if(timer > 1){
            onComplete();
            kill();
        }
    }

    @Override
    public void shapeRender(ShapeRenderer shapeRenderer) {
        super.shapeRender(shapeRenderer);
        shapeRenderer.setColor(getSharedValues().bgColor);
        shapeRenderer.circle(0, 0, (timer-.5f)*(Game.w));
    }

    public void onComplete(){

    }
}
