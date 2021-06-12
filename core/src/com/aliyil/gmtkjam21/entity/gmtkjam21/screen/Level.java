package com.aliyil.gmtkjam21.entity.gmtkjam21.screen;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.core.Screen;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Character;
import com.aliyil.gmtkjam21.entity.gmtkjam21.GameObjectGrid;
import com.aliyil.gmtkjam21.entity.gmtkjam21.PlayerControl;
import com.badlogic.gdx.graphics.Color;

public class Level extends Screen {
    private final GameObjectGrid tileGrid;

    protected Character masterCharacter;
    protected Character slaveCharacter;
    private PlayerControl playerControl;

    public Level(Game game) {
        super(game);
        tileGrid = new GameObjectGrid();
    }

    @Override
    public void start() {
        super.start();

        masterCharacter = new Character(getGameInstance());
        slaveCharacter = new Character(getGameInstance());

        masterCharacter.setColor(Color.CORAL);
        slaveCharacter.setColor(Color.CYAN);


        playerControl = new PlayerControl(getGameInstance());
        playerControl.setMaster(masterCharacter);
        playerControl.setSlave(slaveCharacter);
        playerControl.start();

    }

    protected GameObjectGrid getTileGrid() {
        return tileGrid;
    }

    @Override
    public void stop() {
        super.stop();
        getTileGrid().killAll();
        playerControl.kill();
    }
}
