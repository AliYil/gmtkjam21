package com.aliyil.gmtkjam21;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public final class ResourceManager {
    //Resources
    public BitmapFont bitmapFont;

    public Texture grass1;
    public Texture grass2;
    public Texture tree1;
    public Texture tree2;

    public TextureAtlas duck1;
    public TextureAtlas duck2;
    public TextureAtlas goal;

    public Sound jump;
    public Sound win;
    public Sound beep;

    private Game gameInstance;
    private AssetManager assetManager;

    ResourceManager(Game gameInstance) {
        this.gameInstance = gameInstance;

        assetManager = new AssetManager();
        Texture.setAssetManager(assetManager);

    }

    void loadResources() {
        assetManager.load("fonts/font.fnt", BitmapFont.class);

        assetManager.load("sprites/grass1.png", Texture.class);
        assetManager.load("sprites/grass2.png", Texture.class);
        assetManager.load("sprites/tree1.png", Texture.class);
        assetManager.load("sprites/tree2.png", Texture.class);

        assetManager.load("sprites/duck1.atlas", TextureAtlas.class);
        assetManager.load("sprites/duck2.atlas", TextureAtlas.class);
        assetManager.load("sprites/goal.atlas", TextureAtlas.class);
//
        assetManager.load("sounds/jump.wav", Sound.class);
        assetManager.load("sounds/win.wav", Sound.class);
        assetManager.load("sounds/beep.wav", Sound.class);

        gameInstance.getParticleEffectManager().loadResources();
    }

    public float getProgress() {
        return assetManager.getProgress();
    }

    public void finishLoading() {
        bitmapFont = assetManager.get("fonts/font.fnt", BitmapFont.class);
        bitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        grass1 = assetManager.get("sprites/grass1.png", Texture.class);
        grass2 = assetManager.get("sprites/grass2.png", Texture.class);
        tree1 = assetManager.get("sprites/tree1.png", Texture.class);
        tree2 = assetManager.get("sprites/tree2.png", Texture.class);

        duck1 = assetManager.get("sprites/duck1.atlas", TextureAtlas.class);
        duck2 = assetManager.get("sprites/duck2.atlas", TextureAtlas.class);
        goal = assetManager.get("sprites/goal.atlas", TextureAtlas.class);

        jump = assetManager.get("sounds/jump.wav", Sound.class);
        win = assetManager.get("sounds/win.wav", Sound.class);
        beep = assetManager.get("sounds/beep.wav", Sound.class);
    }

    void dispose() {
        assetManager.dispose();
        gameInstance.getParticleEffectManager().releaseResources();
    }

    public boolean isLoaded() {
        return assetManager.update();
    }
}
