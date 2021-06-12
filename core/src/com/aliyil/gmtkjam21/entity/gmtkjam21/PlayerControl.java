package com.aliyil.gmtkjam21.entity.gmtkjam21;

import com.aliyil.gmtkjam21.Game;
import com.aliyil.gmtkjam21.entity.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class PlayerControl extends Entity {
    private Character master;
    private Character slave;

    public PlayerControl(Game game) {
        super(game);
        enableInputListener(0);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.UP:
            case Input.Keys.W:
                move(0);
                break;
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                move(1);
                break;
            case Input.Keys.DOWN:
            case Input.Keys.S:
                move(2);
                break;
            case Input.Keys.LEFT:
            case Input.Keys.A:
                move(3);
                break;
        }
        return super.keyDown(keycode);
    }

    private int reverseDirection(int direction){
        switch (direction){
            case 0: return 2;
            case 1: return 3;
            case 2: return 0;
            case 3: return 1;
        }
        throw new RuntimeException("Invalid direction");
    }

    private void move(int direction){
        master.move(direction);
        slave.move(reverseDirection(direction));
    }

    public void setMaster(Character master) {
        this.master = master;
    }

    public void setSlave(Character slave) {
        this.slave = slave;
    }
}
