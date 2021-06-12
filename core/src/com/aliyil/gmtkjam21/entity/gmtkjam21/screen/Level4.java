package com.aliyil.gmtkjam21.entity.gmtkjam21.screen;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Floor;
import com.aliyil.gmtkjam21.entity.gmtkjam21.GameObjectGrid;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Pit;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Wall;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Level4 extends Level {
    public Level4(Game game) {
        super(game);
    }

    @Override
    public void start() {
        super.start();

        getTileGrid().addObject(masterCharacter, -3, 4);
        getTileGrid().addObject(slaveCharacter, 3, -2);
        addGoal(0, 0);

        getTileGrid().addObject(new Wall(getGameInstance()), 0, -2);
        getTileGrid().addObject(new Wall(getGameInstance()), 0, -1);
        getTileGrid().addObject(new Wall(getGameInstance()), 0, 1);
        getTileGrid().addObject(new Wall(getGameInstance()), 0, 2);
        getTileGrid().addObject(new Wall(getGameInstance()), 0, 3);
        getTileGrid().addObject(new Wall(getGameInstance()), 0, 4);
        getTileGrid().addObject(new Wall(getGameInstance()), 0, 5);

        getTileGrid().addObject(new Wall(getGameInstance()), 3, 3);
        getTileGrid().addObject(new Wall(getGameInstance()), -4, 0);
        getTileGrid().addObject(new Wall(getGameInstance()), 3, -1);
        getTileGrid().addObject(new Wall(getGameInstance()), 1, -2);

        getTileGrid().addObject(new Pit(getGameInstance()), -3, 3);
        getTileGrid().addObject(new Pit(getGameInstance()), -3, -2);

        int widthSize = 6;
        int heightSize = 4;
        for (int i = -widthSize; i <= widthSize; i++) {
            for (int j = -heightSize+1; j <= heightSize+1; j++) {
                getTileGrid().addObject(new Floor(getGameInstance()), i, j);

                if(i == -widthSize || i == widthSize || j == -heightSize+1 || j == heightSize+1)
                    getTileGrid().addObject(new Wall(getGameInstance()), i, j);
            }
        }

        getTileGrid().startAll();

        OrthographicCamera camera = getGameInstance().getCamera();
        camera.position.set(0, GameObjectGrid.cellSize, 0);
        camera.zoom = .8f;
    }

    @Override
    public void toNextLevel() {
        new Level5(getGameInstance()).start();
    }

    @Override
    public void restart() {
        new Level4(getGameInstance()).start();
    }
}
