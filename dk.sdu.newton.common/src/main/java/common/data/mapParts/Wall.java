package common.data.mapParts;

import common.data.Entity;
import common.data.Hostility;
import common.data.Sprite;
import common.services.Collidable;

import static common.data.Hostility.PASSIVE;

/**
 * The Entity: wall
 * is the blueprint for all doors in the game. Functions to limit the movement on the
 * map to specific rooms and can not be penetrated
 */

public class Wall extends Entity implements Collidable {


    private Sprite sprite;


    public Wall(String name, float x, float y, float width, float height) {
        sprite = new Sprite("Wall.png", 0, 0, width, height);
        location[0] = x;
        location[1] = y;
    }


    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public Hostility getHostility() {
        return PASSIVE;
    }

    @Override
    public void collidesWith(Collidable source) {
    }

    @Override
    public float[] getBounds() {
        return defaultBounds();
    }
}