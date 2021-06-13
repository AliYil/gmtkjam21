package com.aliyil.gmtkjam21.entity.gmtkjam21;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.Utilities;
import com.aliyil.gmtkjam21.entity.core.SpriteEntity;
import com.badlogic.gdx.graphics.Texture;

public class Cloud extends SpriteEntity {
    public Cloud(Game game, Texture texture) {
        super(game, texture);
        resizeWidth(800);
        zIndex = -2;
        getSprite().setOriginCenter();
    }

    @Override
    public void tick() {
        super.tick();
        translateX(-dts()*50);

        if(getX() < -1500){
            translateX(3000);
            setY(Utilities.RANDOM.nextFloat()*800 - 400);
        }
    }
}
