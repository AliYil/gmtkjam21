package com.aliyil.gmtkjam21.entity.core;

import com.aliyil.gmtkjam21.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class GameObject extends Entity {

    private Vector2 pos;
    public Vector2 speed;
    public Vector2 acceleration;

    private Color color;
    public boolean renderingEnabled;
    private boolean isMoving;

    public GameObject(Game game) {
        super(game);
        pos = new Vector2();
        speed = new Vector2();
        acceleration = new Vector2();
        this.color = new Color(Color.WHITE);
    }

    @Override
    public void start() {
        super.start();
        this.renderingEnabled = true;
    }

    @Override
    public void tick() {
        super.tick();
        if (isMoving) {
            calculateMovings();
        }
    }

    @Override
    public void stop() {
        super.stop();
        renderingEnabled = false;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void enableMoving(boolean moving) {
        isMoving = moving;
    }

    protected void calculateMovings() {
        speed.x += acceleration.x * dts();
        speed.y += acceleration.y * dts();

        translate(
                speed.x * dts(),
                speed.y * dts());
    }

    public Vector2 getPosVector() {
        return pos;
    }

    public float getY() {
        return pos.y;
    }

    public void setY(float y) {
        this.pos.y = y;
        onPositionUpdate();
    }

    public float getX() {
        return pos.x;
    }

    public void setX(float x) {
        this.pos.x = x;
        onPositionUpdate();
    }

    public float getAlpha() {
        return color.a;
    }

    public void setAlpha(float alpha) {
        getColor().a = alpha;
    }

    public void setPosition(float x, float y) {
        setX(x);
        setY(y);
    }

    public void setPosition(Vector2 vec) {
        setPosition(vec.x, vec.y);
    }

    public void translateX(float x) {
        setX(getX() + x);
    }

    public void translateY(float y) {
        setY(getY() + y);
    }

    public void translate(float x, float y) {
        translateX(x);
        translateY(y);
    }

    protected void onPositionUpdate() {
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public void render(Batch batch) {
    }

    public void shapeRender(ShapeRenderer shapeRenderer) {
    }
}
