package com.aliyil.gmtkjam21.entity.gmtkjam21.screen;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Character;

public class Level1 extends Level {
    public Level1(Game game) {
        super(game);
    }

    @Override
    public void start() {
        super.start();
        getTileGrid().addObject(masterCharacter, 0, 2);
        getTileGrid().addObject(slaveCharacter, 0, -2);
        getTileGrid().startAll();
    }
}
