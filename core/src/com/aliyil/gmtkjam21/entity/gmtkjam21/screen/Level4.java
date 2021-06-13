package com.aliyil.gmtkjam21.entity.gmtkjam21.screen;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.core.SpriteEntity;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Floor;
import com.aliyil.gmtkjam21.entity.gmtkjam21.GameObjectGrid;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Pit;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Wall;
import com.badlogic.gdx.graphics.Color;
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

        if(!getSharedValues().restarted){
            SpriteEntity restart = new SpriteEntity(getGameInstance(), getGameInstance().getResourceManager().restart){
                private float timer = 0;

                @Override
                public void start() {
                    resizeWidth(GameObjectGrid.cellSize*3);
                    setColor(Color.CHARTREUSE);
                    super.start();
                }

                @Override
                public void tick() {
                    super.tick();

                    timer += dts();
                    if(timer > 6){
                        kill();
                    }

                    setAlpha(timer > 2 || (int)(timer*6) % 2 == 0 ? 1 : 0);

                    if(!Level4.this.isLiving())
                        kill();
                }
            };
            restart.zIndex = 6;
            restart.getSprite().setOriginCenter();
            restart.start();
            restart.setPosition(0, 400);
        }

    }

    @Override
    public void toNextLevel() {
        new Level5(getGameInstance()).start();
    }

    @Override
    public void restart() {
        getSharedValues().restarted = true;
        new Level4(getGameInstance()).start();
    }
}
