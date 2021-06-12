package com.aliyil.gmtkjam21.entity.gmtkjam21.screen;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Floor;
import com.aliyil.gmtkjam21.entity.gmtkjam21.GameObjectGrid;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Pit;
import com.aliyil.gmtkjam21.entity.gmtkjam21.PlayerControl;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Wall;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Level5 extends Level {
    public Level5(Game game) {
        super(game);
    }

    @Override
    public void start() {
        super.start();

        getTileGrid().addObject(masterCharacter, -7, -1);
        getTileGrid().addObject(slaveCharacter, 8, 0);
        addGoal(0, 0);

        getTileGrid().addObject(new Wall(getGameInstance()), 0, -2);
        getTileGrid().addObject(new Wall(getGameInstance()), 0, -1);
        getTileGrid().addObject(new Wall(getGameInstance()), 0, 1);
        getTileGrid().addObject(new Wall(getGameInstance()), 0, 2);

//        getTileGrid().addObject(new Wall(getGameInstance()), -8, 0);
        getTileGrid().addObject(new Wall(getGameInstance()), -8, 1);
        getTileGrid().addObject(new Wall(getGameInstance()), -8, 2);

        getTileGrid().addObject(new Pit(getGameInstance()), 7, 0);
        getTileGrid().addObject(new Pit(getGameInstance()), 8, -1);
        getTileGrid().addObject(new Pit(getGameInstance()), 8, 2);
        getTileGrid().addObject(new Pit(getGameInstance()), 7, 2);
        getTileGrid().addObject(new Pit(getGameInstance()), 6, 2);
        getTileGrid().addObject(new Pit(getGameInstance()), 7, -1);
        getTileGrid().addObject(new Wall(getGameInstance()), 5, 1);
        getTileGrid().addObject(new Pit(getGameInstance()), 5, 0);
        getTileGrid().addObject(new Wall(getGameInstance()), 6, -2);
        getTileGrid().addObject(new Pit(getGameInstance()), 5, -2);
        getTileGrid().addObject(new Pit(getGameInstance()), 4, -2);
        getTileGrid().addObject(new Pit(getGameInstance()), 3, -1);
        getTileGrid().addObject(new Pit(getGameInstance()), 3, 0);
        getTileGrid().addObject(new Wall(getGameInstance()), 4, 2);
        getTileGrid().addObject(new Pit(getGameInstance()), 3, 2);
        getTileGrid().addObject(new Wall(getGameInstance()), 2, 2);
        getTileGrid().addObject(new Pit(getGameInstance()), 1, 1);
        getTileGrid().addObject(new Pit(getGameInstance()), 1, -1);
        getTileGrid().addObject(new Pit(getGameInstance()), 2, -1);

        int widthSize = 9;
        int heightSize = 3;
        for (int i = -widthSize; i <= widthSize; i++) {
            for (int j = -heightSize; j <= heightSize; j++) {
                getTileGrid().addObject(new Floor(getGameInstance()), i, j);

                if(i == -widthSize || i == widthSize || j == -heightSize || j == heightSize)
                    getTileGrid().addObject(new Wall(getGameInstance()), i, j);
            }
        }

        getTileGrid().startAll();

        OrthographicCamera camera = getGameInstance().getCamera();
        camera.position.set(0, GameObjectGrid.cellSize*0, 0);
        camera.zoom = .8f;

//        PlayerControl playerControl = (PlayerControl) getGameInstance().getEntityOrNull(PlayerControl.class);
//        playerControl.isCharactersAligned = true;
    }

    @Override
    public void toNextLevel() {
        new Level6(getGameInstance()).start();
    }

    @Override
    public void restart() {
        new Level5(getGameInstance()).start();
    }
}
