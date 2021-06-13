package com.aliyil.gmtkjam21.entity.gmtkjam21.screen;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.core.Screen;
import com.aliyil.gmtkjam21.entity.core.SpriteEntity;
import com.aliyil.gmtkjam21.entity.core.Text;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Character;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class End extends Screen {
    public End(Game game) {
        super(game);
        enableInputListener(0);
    }

    @Override
    public void start() {
        super.start();

        Character master = new Character(getGameInstance(), getGameInstance().getResourceManager().duck2.getRegions());
        master.resizeWidth(100);
        master.setPosition(-400, 0);
        master.start();
        master.flipHorizontal = true;
        addToScreenEntities(master);

        Character slave = new Character(getGameInstance(), getGameInstance().getResourceManager().duck1.getRegions());
        slave.resizeWidth(100);
        slave.setPosition(400, 0);
        slave.start();
        addToScreenEntities(slave);

        Text desc = new Text(getGameInstance(), "CONGRAGULATIONS!");
        desc.setCentered(true);
        desc.setScale(5.5f);
        desc.setPosition(0, 400);
        desc.start();
        desc.setColor(new Color(0xCA61DCFF));
        addToScreenEntities(desc);

        Text desc1 = new Text(getGameInstance(), "You've finished all levels.");
        desc1.setCentered(true);
        desc1.setScale(3.5f);
        desc1.setPosition(0, 300);
        desc1.start();
        desc1.setColor(new Color(0xCA61DCFF));
        addToScreenEntities(desc1);

        Text desc2 = new Text(getGameInstance(), "Thank you for playing!");
        desc2.setCentered(true);
        desc2.setScale(3.5f);
        desc2.setPosition(0, 0);
        desc2.start();
        desc2.setColor(new Color(0xCA61DCFF));
        addToScreenEntities(desc2);

        Text desc3 = new Text(getGameInstance(), "Press any key!"){
            private float timer = 0;
            @Override
            public void tick() {
                super.tick();
                timer += dts();
                renderingEnabled = (int)(timer*2) % 2 == 0;
            }
        };
        desc3.setCentered(true);
        desc3.setScale(3.5f);
        desc3.setPosition(0, -300);
        desc3.start();
        desc3.setColor(new Color(0xCA61DCFF));
        addToScreenEntities(desc3);

        OrthographicCamera camera = getGameInstance().getCamera();
        camera.position.set(0, 0, 0);
        camera.zoom = 1f;

//        new Level6(getGameInstance()).start();
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public boolean keyDown(int keycode) {
        new Main(getGameInstance()).start();
        return super.keyDown(keycode);
    }
}
