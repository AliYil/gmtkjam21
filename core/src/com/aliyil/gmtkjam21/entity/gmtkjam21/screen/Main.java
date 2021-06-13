package com.aliyil.gmtkjam21.entity.gmtkjam21.screen;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.core.Screen;
import com.aliyil.gmtkjam21.entity.core.SpriteEntity;
import com.aliyil.gmtkjam21.entity.core.Text;
import com.aliyil.gmtkjam21.entity.gmtkjam21.Character;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Main extends Screen {
    public Main(Game game) {
        super(game);
        enableInputListener(0);
    }

    @Override
    public void start() {
        super.start();

        SpriteEntity title = new SpriteEntity(getGameInstance(), getGameInstance().getResourceManager().logo);
        title.resizeWidth(950);
        title.setPosition(0, 300);
        title.start();
        addToScreenEntities(title);

        Character master = new Character(getGameInstance(), getGameInstance().getResourceManager().duck2.getRegions());
        master.resizeWidth(100);
        master.setPosition(-600, 300);
        master.start();
        master.flipHorizontal = true;
        addToScreenEntities(master);

        Character slave = new Character(getGameInstance(), getGameInstance().getResourceManager().duck1.getRegions());
        slave.resizeWidth(100);
        slave.setPosition(600, 300);
        slave.start();
        addToScreenEntities(slave);


        Text desc = new Text(getGameInstance(), "A game by superbnoobster");
        desc.setCentered(true);
        desc.setScale(3.5f);
        desc.setPosition(0, -400);
        desc.start();
        desc.setColor(new Color(.8f, .4f, 1f, 1f));
        addToScreenEntities(desc);

        Text desc2 = new Text(getGameInstance(), "Press any key!"){
            private float timer = 0;
            @Override
            public void tick() {
                super.tick();
                timer += dts();
                renderingEnabled = (int)(timer*2) % 2 == 0;
            }
        };
        desc2.setCentered(true);
        desc2.setScale(3.5f);
        desc2.setPosition(0, 0);
        desc2.start();
        desc2.setColor(new Color(0xCA61DCFF));
        addToScreenEntities(desc2);

        OrthographicCamera camera = getGameInstance().getCamera();
        camera.position.set(0, 0, 0);
        camera.zoom = 1f;

        getSharedValues().restarted = false;

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
//        if(keycode == Input.Keys.SPACE){
//            getGameInstance().getSoundManager().win();
//            new Level1(getGameInstance()).start();
//        }

        getGameInstance().getSoundManager().win();
        new Level1(getGameInstance()).start();
        return super.keyDown(keycode);
    }
}
