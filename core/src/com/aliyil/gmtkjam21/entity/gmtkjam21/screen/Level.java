package com.aliyil.gmtkjam21.entity.gmtkjam21.screen;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.core.Screen;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Character;
import com.aliyil.gmtkjam21.entity.gmtkjam21.GameObjectGrid;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Goal;
import com.aliyil.gmtkjam21.entity.gmtkjam21.LevelStart;
import com.aliyil.gmtkjam21.entity.gmtkjam21.PlayerControl;
import com.badlogic.gdx.graphics.Color;

public abstract class Level extends Screen {
    private final GameObjectGrid tileGrid;

    protected Character masterCharacter;
    protected Character slaveCharacter;
    protected PlayerControl playerControl;

    public Level(Game game) {
        super(game);
        tileGrid = new GameObjectGrid();
    }

    @Override
    public void start() {
        super.start();

        masterCharacter = new Character(getGameInstance(), getGameInstance().getResourceManager().duck2.getRegions());
        slaveCharacter = new Character(getGameInstance(), getGameInstance().getResourceManager().duck1.getRegions());
//        slaveCharacter.setStateTime(.25f);

        playerControl = new PlayerControl(getGameInstance());
        playerControl.setMaster(masterCharacter);
        playerControl.setSlave(slaveCharacter);
        playerControl.start();

        new LevelStart(getGameInstance()).start();

    }

    public GameObjectGrid getTileGrid() {
        return tileGrid;
    }

    protected void addGoal(int x, int y){
        Goal goal = new Goal(getGameInstance());
        getTileGrid().addObject(goal, x, y);
    }

    @Override
    public void stop() {
        super.stop();
        getTileGrid().killAll();
        playerControl.kill();
    }

    public abstract void toNextLevel();
    public abstract void restart();
}
