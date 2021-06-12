package com.aliyil.gmtkjam21;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class ParticleEffectManager {

    private Array<PooledEntityEffect> effects;
    private final Game gameInstance;

    private ParticleEffectPool starsParticlePool;

    public ParticleEffectManager(Game gameInstance) {
        this.gameInstance = gameInstance;
    }

    public void renderAllParticles(Batch batch) {
        for (PooledEntityEffect entityEffect : effects) {
            if (entityEffect.parentGameObject != null) {
                if (!entityEffect.parentGameObject.isRunning()) {
                    entityEffect.effect.getEmitters().first().setContinuous(false);
                } else {
                    entityEffect.effect.setPosition(entityEffect.parentGameObject.getX(), entityEffect.parentGameObject.getY());
                }
            }
            entityEffect.effect.draw(batch, entityEffect.respectPause ? gameInstance.sharedValues.paused ? 0 : S.dts(gameInstance.sharedValues.gameSpeed) : S.dts(gameInstance.sharedValues.gameSpeed));

            if (!entityEffect.effect.getEmitters().first().isContinuous() && entityEffect.effect.isComplete()) {
                effects.removeValue(entityEffect, true);
                entityEffect.effect.free();
            }
        }
    }

    public void loadResources() {
        effects = new Array<PooledEntityEffect>();

        ParticleEffect starsParticle = new ParticleEffect();
        starsParticle.load(Gdx.files.internal("particles/stars.p"), Gdx.files.internal("sprites"));
        starsParticle.start();
        starsParticle.scaleEffect(2f);
        starsParticlePool = new ParticleEffectPool(starsParticle, 1, 2);
    }

    public void releaseResources() {
    }

    public int DBG_getTotalParticles() {
        int returnValue = 0;
        for (PooledEntityEffect entityEffect : effects) {
            for (ParticleEmitter emitter : entityEffect.effect.getEmitters()) {
                returnValue += emitter.getActiveCount();
            }
        }
        return returnValue;
    }

    //Should call setCountinuous(true) for all continuous particles
//
    public void newStars(Vector2 pos) {
        ParticleEffectPool.PooledEffect pooledEffect = starsParticlePool.obtain();
        pooledEffect.setPosition(pos.x, pos.y);
//        pooledEffect.getEmitters().first().getTransparency().setHigh(1f);
        PooledEntityEffect entityEffect = new PooledEntityEffect(pooledEffect);
        effects.add(entityEffect);
    }
}
