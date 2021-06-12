package com.aliyil.gmtkjam21;

import com.badlogic.gdx.audio.Sound;

public final class SoundManager {
    private Game gameInstance;

    SoundManager(Game gameInstance) {
        this.gameInstance = gameInstance;
    }

    public void jump() {
        float vol = 1f;
        Sound s = gameInstance.getResourceManager().jump;
        s.stop();
        s.play(vol);
    }
    public void jump2() {
        float vol = 1f;
        Sound s = gameInstance.getResourceManager().jump;
        s.stop();
        s.play(vol, .8f, 0);
    }

    public void win() {
        float vol = 1f;
        Sound s = gameInstance.getResourceManager().win;
        s.play(vol);
    }

//    public void startBackground() {
//        float vol = 1f;
//        Sound s = gameInstance.getResourceManager().backgroundSound;
//        s.loop(vol);
//    }
//
//    public void stopBackground() {
//        Sound s = gameInstance.getResourceManager().backgroundSound;
//        s.stop();
//    }
}
