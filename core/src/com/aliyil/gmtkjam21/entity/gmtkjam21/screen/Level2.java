package com.aliyil.gmtkjam21.entity.gmtkjam21.screen;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Floor;
import com.aliyil.gmtkjam21.entity.gmtkjam21.GameObjectGrid;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Goal;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Wall;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Level2 extends Level {
    public Level2(Game game) {
        super(game);
    }

    @Override
    public void start() {
        super.start();
        getTileGrid().addObject(masterCharacter, -2, 2);
        getTileGrid().addObject(slaveCharacter, 1, -2);

        addGoal(0, 0);


        getTileGrid().addObject(new Wall(getGameInstance()), -2, 0);
        getTileGrid().addObject(new Wall(getGameInstance()), -1, 0);

        getTileGrid().addObject(new Wall(getGameInstance()), -2, -1);
        getTileGrid().addObject(new Wall(getGameInstance()), -1, -1);

        getTileGrid().addObject(new Wall(getGameInstance()), 1, 0);
        getTileGrid().addObject(new Wall(getGameInstance()), 2, 0);


        int size = 3;
        for (int i = -size; i <= size; i++) {
            for (int j = -size; j <= size; j++) {
                getTileGrid().addObject(new Floor(getGameInstance()), i, j);

                if(i == -size || i == size || j == -size || j == size)
                    getTileGrid().addObject(new Wall(getGameInstance()), i, j);
            }
        }

        getTileGrid().startAll();

        OrthographicCamera camera = getGameInstance().getCamera();
        camera.position.set(0, 0, 0);
        camera.zoom = .55f;
    }

    @Override
    public void toNextLevel() {
        new Level3(getGameInstance()).start();
    }

    @Override
    public void restart() {
        new Level2(getGameInstance()).start();
    }
}
