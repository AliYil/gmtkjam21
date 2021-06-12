package com.aliyil.gmtkjam21;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.utils.Array;

public class ParticleEffectManager {

    private Array<PooledEntityEffect> effects;
    private Game gameInstance;

//    private ParticleEffectPool growingCirclePool;

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

//        ParticleEffect growingCircle = new ParticleEffect();
//        growingCircle.load(Gdx.files.internal("particles/growingcircle.p"), Gdx.files.internal("textures"));
//        growingCircle.start();
//        growingCircle.scaleEffect(1f);
//        growingCirclePool = new ParticleEffectPool(growingCircle, 10, 100);
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
//    public void newGrowingCircle(float x, float y, float alpha) {
//        ParticleEffectPool.PooledEffect pooledEffect = growingCirclePool.obtain();
//        pooledEffect.setPosition(x, y);
//        pooledEffect.getEmitters().first().getTransparency().setHigh(alpha);
//        PooledEntityEffect entityEffect = new PooledEntityEffect(pooledEffect);
//        effects.add(entityEffect);
//    }
}
