package com.aliyil.gmtkjam21.entity.gmtkjam21.screen;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.core.SpriteEntity;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Floor;
import com.aliyil.gmtkjam21.entity.gmtkjam21.GameObjectGrid;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Pit;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Wall;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class LevelCustom extends Level {
    public LevelCustom(Game game) {
        super(game);
    }

    @Override
    public void start() {
        super.start();

        getTileGrid().addObject(masterCharacter, -3, 3);
        getTileGrid().addObject(slaveCharacter, 3, -3);

        getTileGrid().addObject(new Pit(getGameInstance()), 1, -1);

//        addGoal(0, 0);

        for (int i = -5; i <= 5; i++) {
            for (int j = -5; j <= 5; j++) {
                getTileGrid().addObject(new Floor(getGameInstance()), i, j);
            }
        }

        getTileGrid().startAll();

        OrthographicCamera camera = getGameInstance().getCamera();
        camera.position.set(0, 0, 0);
        camera.zoom = .7f;
    }

    @Override
    public void toNextLevel() {
        new End(getGameInstance()).start();
    }

    @Override
    public void restart() {
        new LevelCustom(getGameInstance()).start();
    }
}
