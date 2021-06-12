package com.aliyil.gmtkjam21;

import com.aliyil.gmtkjam21.entity.core.GameObject;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;

public class PooledEntityEffect {
    ParticleEffectPool.PooledEffect effect;
    GameObject parentGameObject;
    boolean respectPause = false;

    public PooledEntityEffect(ParticleEffectPool.PooledEffect effect) {
        this.effect = effect;
    }
}
