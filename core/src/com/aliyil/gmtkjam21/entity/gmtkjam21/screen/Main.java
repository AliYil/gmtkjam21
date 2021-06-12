package com.aliyil.gmtkjam21.entity.gmtkjam21.screen;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.core.Screen;

public class Main extends Screen {
    public Main(Game game) {
        super(game);
    }

    @Override
    public void start() {
        super.start();
        new Level1(getGameInstance()).start();
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void stop() {
        super.stop();
    }
}
