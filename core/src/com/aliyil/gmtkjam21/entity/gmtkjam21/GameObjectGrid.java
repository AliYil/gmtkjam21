package com.aliyil.gmtkjam21.entity.gmtkjam21;

import com.aliyil.gmtkjam21.entity.core.GameObject;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class GameObjectGrid {
    public static final float cellSize = 64;
    private final List<GameObject> gameObjects;
    public GameObjectGrid(){
        gameObjects = new ArrayList<>();
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void addObject(GameObject entity, int x, int y){
        getGameObjects().add(entity);
        entity.setPosition(toWorld(new Vector2(x, y)));
    }

    public void removeObject(GameObject entity){
        getGameObjects().remove(entity);
    }

    public List<GameObject> getFromPosition(int x, int y){
        return getFromPosition(new Vector2(x, y));
    }

    public List<GameObject> getFromPosition(Vector2 pos){
        List<GameObject> found = new ArrayList<>(1);
        for (GameObject gameObject : getGameObjects()) {
            if(toGrid(gameObject.getPosVector()).epsilonEquals(pos)){
                found.add(gameObject);
            }
        }
        return found;
    }

    public Vector2 toWorld(Vector2 pos){
        return pos.cpy().scl(cellSize);
    }

    public Vector2 toGrid(Vector2 pos){
        return pos.cpy().scl(1f/cellSize);
    }

    public void killAll(){
        for (GameObject gameObject : getGameObjects()) {
            gameObject.kill();
        }
    }

    public void startAll(){
        for (GameObject gameObject : getGameObjects()) {
            gameObject.start();
        }
    }
}