package com.aliyil.gmtkjam21;

import com.aliyil.gmtkjam21.entity.core.Entity;
import com.aliyil.gmtkjam21.entity.core.GameObject;
import com.aliyil.gmtkjam21.entity.core.InputCalculator;
import com.aliyil.gmtkjam21.entity.core.Screen;
import com.aliyil.gmtkjam21.entity.core.screen.LoadingScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Game extends ApplicationAdapter {
    public static final String ver = "0.0.1a";
    //Width units
    public static final int w = 1920;
    //Height units
    public static final int h = 1080;
    public static final boolean devMode = false;
    public SharedValues sharedValues;

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private ParticleEffectManager particleEffectManager;
    private ResourceManager resourceManager;
    private SoundManager soundManager;
    private OsBridge osBridge;

    private Screen currentScreen;
    private InputMultiplexer inputMultiplexer;
    private List<Entity> entities;
    private List<Entity> addQueue;
    private Iterator<Entity> currentIterator;

    public Game(OsBridge osBridge) {
        this.osBridge = osBridge;
    }

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        entities = new ArrayList<Entity>();
        addQueue = new ArrayList<Entity>();
        inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);
        sharedValues = new SharedValues();
        particleEffectManager = new ParticleEffectManager(this);
        resourceManager = new ResourceManager(this);
        soundManager = new SoundManager(this);

        resourceManager.loadResources();
        camera = new OrthographicCamera();
        camera.position.set(0, 0, 0);
        camera.zoom = 1f;
        viewport = new FitViewport(w, h, camera);
        viewport.apply(false);

        getSharedValues().bgColor =
                new Color(0.7f, 0.7f, 1f, 1f);
//                new Color(0.7f, 0.7f, 0.7f, 1f);

        Entity inputCalc = new InputCalculator(this, viewport);
        inputCalc.start();
        new LoadingScreen(this).start();
        Gdx.input.setCatchBackKey(true);
        getSharedValues().gameElapsed = 0;
    }

    @Override
    public void render() {
        Color bgColor = getSharedValues().bgColor;
        Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, bgColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT
                | GL20.GL_DEPTH_BUFFER_BIT
                | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));

        camera.update();

        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        //draw bg
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.setColor(getSharedValues().bgColor);
//        shapeRenderer.rect(0, 0, w, h);
//        shapeRenderer.end();

        spriteBatch.begin();
        getParticleEffectManager().renderAllParticles(spriteBatch);
        currentIterator = entities.listIterator();
        while (currentIterator.hasNext()) {
            Entity entity = currentIterator.next();
            if (!entity.isLiving()) {
                removeFromIterator();
            } else if (entity.isRunning()) {
                entity.tick();
            }
            if(entity instanceof GameObject){
                 GameObject gameObject = (GameObject)entity;
                if (gameObject.renderingEnabled)
                    gameObject.render(spriteBatch);
            }
        }
        currentIterator = null;
        spriteBatch.end();

        spriteBatch.setProjectionMatrix(camera.view);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        currentIterator = entities.listIterator();
        while (currentIterator.hasNext()) {
            Entity entity = currentIterator.next();
            if(entity instanceof GameObject){
                GameObject gameObject = (GameObject)entity;
                if (gameObject.renderingEnabled) {
                    shapeRenderer.setColor(gameObject.getColor());
                    gameObject.shapeRender(shapeRenderer);
                }
            }
        }
        currentIterator = null;
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        shapeRenderer.end();

        //add entities in queue
        for (Entity entity : addQueue) {
            ListIterator<Entity> iterator = entities.listIterator();
            while (iterator.hasNext()) {
                if (iterator.next().zIndex >= entity.zIndex)
                    break;
            }
            iterator.add(entity);
        }
        addQueue.clear();

        getSharedValues().gameElapsed += S.d();
    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height, false);
    }

    @Override
    public void dispose() {
        getResourceManager().dispose();
        spriteBatch.dispose();
        shapeRenderer.dispose();
        super.dispose();
    }

    public <TEntity extends Entity> void addEntity(TEntity entity) {
        addQueue.add(entity);
    }

    public void addToInputMultiplexer(Entity entity) {
        Array<InputProcessor> processors = inputMultiplexer.getProcessors();
        int indexToPut;
        for (indexToPut = 0; indexToPut < processors.size; indexToPut++) {
            int index = ((Entity) processors.get(indexToPut)).getInputIndex();
            if (index >= entity.getInputIndex()) {
                break;
            }
        }
        if (indexToPut >= processors.size) {
            inputMultiplexer.addProcessor(entity);
        } else {
            inputMultiplexer.addProcessor(indexToPut, entity);
        }

    }

    public void removeFromInputMultiplexer(Entity entity) {
        inputMultiplexer.removeProcessor(entity);
    }

    public void removeFromIterator() {
        try {
            currentIterator.remove();
        } catch (IllegalStateException e) {
            Gdx.app.error("Warn", "currentIterator.remove() probably called multiple times", e);
        }

    }

    public Screen getCurrentScreen() {
        return currentScreen;
    }

    public void setCurrentScreen(Screen screen) {
        currentScreen = screen;
    }

    public int getTotalRenderableCount() {
        return entities.size();
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public ParticleEffectManager getParticleEffectManager() {
        return particleEffectManager;
    }

    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }

    public SharedValues getSharedValues() {
        return sharedValues;
    }

    public OsBridge getOsBridge() {
        return osBridge;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public <T> Entity getEntityOrNull(Class<T> type){
        for (Entity entity : entities) {
            if(entity.getClass() == type) return entity;
        }
        return null;
    }
}
