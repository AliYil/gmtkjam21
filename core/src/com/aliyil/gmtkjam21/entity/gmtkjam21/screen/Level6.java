package com.aliyil.gmtkjam21.entity.gmtkjam21.screen;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Floor;
import com.aliyil.gmtkjam21.entity.gmtkjam21.GameObjectGrid;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Pit;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Wall;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Level6 extends Level {
    public Level6(Game game) {
        super(game);
    }

    @Override
    public void start() {
        super.start();

        getTileGrid().addObject(masterCharacter, -2, 0);
        getTileGrid().addObject(slaveCharacter, 3, 0);
        addGoal(0, 0);

        getTileGrid().addObject(new Pit(getGameInstance()), 0, -2);
        getTileGrid().addObject(new Wall(getGameInstance()), 0, -1);
        getTileGrid().addObject(new Wall(getGameInstance()), 0, 1);
        getTileGrid().addObject(new Pit(getGameInstance()), 0, 2);

        int widthSize = 4;
        int heightSize = 4;
        for (int i = -widthSize; i <= widthSize; i++) {
            for (int j = -heightSize; j <= heightSize; j++) {
                getTileGrid().addObject(new Floor(getGameInstance()), i, j);

                if(i == -widthSize || i == widthSize || j == -heightSize || j == heightSize)
                    getTileGrid().addObject(new Wall(getGameInstance()), i, j);
                if(i == -widthSize+1)
                    getTileGrid().addObject(new Pit(getGameInstance()), i, j);
            }
        }

        getTileGrid().startAll();

        OrthographicCamera camera = getGameInstance().getCamera();
        camera.position.set(0, GameObjectGrid.cellSize*0, 0);
        camera.zoom = .65f;

//        PlayerControl playerControl = (PlayerControl) getGameInstance().getEntityOrNull(PlayerControl.class);
//        playerControl.isCharactersAligned = true;
    }

    @Override
    public void toNextLevel() {
        new Main(getGameInstance()).start();
    }

    @Override
    public void restart() {
        new Level6(getGameInstance()).start();
    }
}
