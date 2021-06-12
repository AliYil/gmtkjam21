package com.aliyil.gmtkjam21;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public final class ResourceManager {
    //Resources
    public BitmapFont bitmapFont;

//    public Texture t;
//
//    public Sound s;

    private Game gameInstance;
    private AssetManager assetManager;

    ResourceManager(Game gameInstance) {
        this.gameInstance = gameInstance;

        assetManager = new AssetManager();
        Texture.setAssetManager(assetManager);

    }

    void loadResources() {
        assetManager.load("fonts/font.fnt", BitmapFont.class);

//        assetManager.load("textures/.png", Texture.class);
//
//        assetManager.load("sounds/.wav", Sound.class);

        gameInstance.getParticleEffectManager().loadResources();
    }

    public float getProgress() {
        return assetManager.getProgress();
    }

    public void finishLoading() {
        bitmapFont = assetManager.get("fonts/font.fnt", BitmapFont.class);
        bitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

//        t = assetManager.get("textures/.png", Texture.class);

//        s = assetManager.get("sounds/.wav", Sound.class);
    }

    void dispose() {
        assetManager.dispose();
        gameInstance.getParticleEffectManager().releaseResources();
    }

    public boolean isLoaded() {
        return assetManager.update();
    }
}
