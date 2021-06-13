package com.aliyil.gmtkjam21.entity.core.screen;

//import DebugInfoText;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Cloud;
import com.aliyil.gmtkjam21.entity.gmtkjam21.screen.End;
import com.aliyil.gmtkjam21.entity.gmtkjam21.screen.Main;
import com.aliyil.gmtkjam21.entity.core.DebugInfoText;
import com.aliyil.gmtkjam21.entity.core.LoadingBar;
import com.aliyil.gmtkjam21.entity.core.Screen;


public class LoadingScreen extends Screen {
    private LoadingBar bar;
    private boolean loaded;

    public LoadingScreen(Game game) {
        super(game);
    }

    @Override
    public void start() {
        super.start();
        bar = new LoadingBar(getGameInstance());
//        bar.setPosition(Game.w * 0.5f, Game.h * 0.5f);
        bar.setPosition(0, 0);
        bar.start();
        loaded = false;
    }

    @Override
    public void tick() {
        super.tick();
        if (!loaded && getGameInstance().getResourceManager().isLoaded()) {
            loaded = true;
            getGameInstance().getResourceManager().finishLoading();

            new Main(getGameInstance()).start();
            if (Game.devMode)
                new DebugInfoText(getGameInstance()).start();

            //add clouds
            Cloud cloud1 = new Cloud(getGameInstance(), getGameInstance().getResourceManager().cloud1);
            cloud1.setPosition(-1000, 400);
            cloud1.start();
            Cloud cloud2 = new Cloud(getGameInstance(), getGameInstance().getResourceManager().cloud2);
            cloud2.setPosition(0, -200);
            cloud2.start();
            Cloud cloud3 = new Cloud(getGameInstance(), getGameInstance().getResourceManager().cloud3);
            cloud3.setPosition(1000, 0);
            cloud3.start();
        }
    }

    @Override
    public void stop() {
        super.stop();
        bar.kill();
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
