package com.aliyil.gmtkjam21.entity.core;

import com.aliyil.gmtkjam21.Game;

import java.util.ArrayList;
import java.util.List;

public abstract class Screen extends Entity {

    private List<Entity> screenEntities;

    private List<Entity> getScreenEntities() {
        return screenEntities;
    }

    public Screen(Game game) {
        super(game);
        screenEntities = new ArrayList<>(30);
    }

    @Override
    public void start() {
        super.start();
        if (getGameInstance().getCurrentScreen() != null && getGameInstance().getCurrentScreen().isLiving() && getGameInstance().getCurrentScreen() != this)
            getGameInstance().getCurrentScreen().kill();
        getGameInstance().setCurrentScreen(this);
    }

    @Override
    public void stop() {
        for (Entity screenEntity : getScreenEntities()) {
            screenEntity.kill();
        }
        super.stop();
    }

    public boolean onBackPressed() {
        return false;
    }

    protected void addToScreenEntities(Entity entity){
        getScreenEntities().add(entity);
    }
}
