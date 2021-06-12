package com.aliyil.gmtkjam21.entity.core;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.S;
import com.aliyil.gmtkjam21.SharedValues;
import com.badlogic.gdx.InputProcessor;

public class Entity implements InputProcessor {
    public int zIndex;
    protected boolean isLiving;
    private boolean isRunning;
    private boolean isInputListener;
    private int inputIndex;
    private Game gameInstance;

    public Entity(Game game) {
        this.gameInstance = game;
        game.addEntity(this);
        isLiving = true;
        isInputListener = false;
    }

    public void start() {
        this.isRunning = true;
        if (isInputListener) {
            getGameInstance().addToInputMultiplexer(this);
        }
    }

    public void tick() {
    }

    public void stop() {
        isRunning = false;
        if (isInputListener) {
            getGameInstance().removeFromInputMultiplexer(this);
        }
    }

    protected void enableInputListener(int index) {
        if (!isInputListener) {
            inputIndex = index;
            isInputListener = true;
            if (isRunning()) {
                getGameInstance().addToInputMultiplexer(this);
            }
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void kill() {
        if (isLiving()) {
            isLiving = false;
            stop();
        }
    }

    public boolean isLiving() {
        return isLiving;
    }

    public int getInputIndex() {
        return inputIndex;
    }

    //Utility Methods
    protected Game getGameInstance() {
        return gameInstance;
    }

    protected float dts() {
        return S.dts(getSharedValues().gameSpeed);
    }

    protected SharedValues getSharedValues() {
        return getGameInstance().sharedValues;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
