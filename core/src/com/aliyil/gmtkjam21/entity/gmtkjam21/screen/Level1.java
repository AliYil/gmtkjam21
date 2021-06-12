package com.aliyil.gmtkjam21.entity.gmtkjam21.screen;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.core.SpriteEntity;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Floor;
import com.aliyil.gmtkjam21.entity.gmtkjam21.GameObjectGrid;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Wall;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class Level1 extends Level {
    public Level1(Game game) {
        super(game);
    }

    @Override
    public void start() {
        super.start();

        getTileGrid().addObject(masterCharacter, -3, 3);
        getTileGrid().addObject(slaveCharacter, 3, -3);

        addGoal(0, 0);

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

        OrthographicCamera camera = getGameInstance().getCamera();
        camera.position.set(0, 0, 0);
        camera.zoom = .7f;

        playerControl.characterAlignmentIndicatorEnabled = false;

        SpriteEntity wasd = new SpriteEntity(getGameInstance(), getGameInstance().getResourceManager().wasd){
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

                setPosition(masterCharacter.getPosVector());

                timer += dts();
                if(timer > 6){
                    kill();
                }

                setAlpha(timer > 2 || (int)(timer*6) % 2 == 0 ? 1 : 0);
            }
        };
        wasd.zIndex = 6;
        wasd.start();
    }

    @Override
    public void toNextLevel() {
        new Level2(getGameInstance()).start();
    }

    @Override
    public void restart() {
        new Level1(getGameInstance()).start();
    }
}
