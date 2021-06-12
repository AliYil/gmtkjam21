package com.aliyil.gmtkjam21;

import com.badlogic.gdx.Gdx;

public final class S {
    //Delta Time
    public static float d() {
        return Math.min(Gdx.graphics.getDeltaTime(), 1f/30f);
    }

    //Frames Per Second
    public static float fps() {
        return Gdx.graphics.getFramesPerSecond();
    }

    //Delta Time * Speed
    public static float dts(float speed) {
        return d() * speed;
    }
}
