package com.aliyil.gmtkjam21.entity.gmtkjam21.screen;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Floor;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Goal;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Wall;

public class Level1 extends Level {
    public Level1(Game game) {
        super(game);
    }

    @Override
    public void start() {
        super.start();
        getTileGrid().addObject(masterCharacter, -3, 3);
        getTileGrid().addObject(slaveCharacter, 3, -3);

        getTileGrid().addObject(new Goal(getGameInstance()), 0, 0);

        getTileGrid().addObject(new Wall(getGameInstance()), -4, 0);
        getTileGrid().addObject(new Wall(getGameInstance()), -3, 0);
        getTileGrid().addObject(new Wall(getGameInstance()), -2, 0);
        getTileGrid().addObject(new Wall(getGameInstance()), -1, 0);
        getTileGrid().addObject(new Wall(getGameInstance()), 1, 0);
        getTileGrid().addObject(new Wall(getGameInstance()), 2, 0);
        getTileGrid().addObject(new Wall(getGameInstance()), 3, 0);
        getTileGrid().addObject(new Wall(getGameInstance()), 4, 0);

        getTileGrid().addObject(new Wall(getGameInstance()), -1, 2);
        getTileGrid().addObject(new Wall(getGameInstance()), 0, 2);
        getTileGrid().addObject(new Wall(getGameInstance()), 1, 2);

        getTileGrid().addObject(new Wall(getGameInstance()), -1, -2);
        getTileGrid().addObject(new Wall(getGameInstance()), 0, -2);
        getTileGrid().addObject(new Wall(getGameInstance()), 1, -2);

        for (int i = -5; i <= 5; i++) {
            for (int j = -5; j <= 5; j++) {
                getTileGrid().addObject(new Floor(getGameInstance()), i, j);

                if(i == -5 || i == 5 || j == -5 || j == 5)
                    getTileGrid().addObject(new Wall(getGameInstance()), i, j);
            }
        }

        getTileGrid().startAll();
    }

    @Override
    public void toNextLevel() {
        new End(getGameInstance()).start();
    }
}
