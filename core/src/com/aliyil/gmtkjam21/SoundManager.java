package com.aliyil.gmtkjam21;

public final class SoundManager {
    private Game gameInstance;

    SoundManager(Game gameInstance) {
        this.gameInstance = gameInstance;
    }

//    public void note1(float pitch) {
//        float vol = 1f;
//        Sound s = gameInstance.getResourceManager().note1;
//        s.play(vol, pitch, 0);
//    }

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
