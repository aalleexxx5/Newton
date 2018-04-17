package dk.sdu.newton.apprentice;

import common.data.*;
import common.services.Collidable;
import common.services.Updatable;

import static common.data.Hostility.KILLS_ENEMY;
import static common.data.Hostility.KILLS_PLAYER;
import static common.data.Hostility.PASSIVE;

public class Bullet extends Projectile {
    private static final String FILENAME = "apprenticebullet.png";
    private static final int WIDTH = 16;
    private static final int HEIGHT = 16;
    private static final float SPEED = 250f;
	private boolean shouldDestruct = false;
    
    public Bullet(float x, float y, ProjectileDirection direction, Hostility bulletHostility){
	    super(direction, SPEED, bulletHostility);
	    location[0] = x;
	    location[1] = y;
    }
    
    @Override
    public Sprite getSprite() {
        return new Sprite(FILENAME, 0,0,WIDTH, HEIGHT);
    }

    @Override
    public Enum getHostility() {
        return KILLS_PLAYER;
    }

    @Override
    public void collidesWith(Collidable source) {
        if (source.getHostility() == PASSIVE) {
            setDestruct();
        } else if (source.getHostility() == KILLS_PLAYER) {
            setDestruct();
        } else if (source.getHostility() == KILLS_ENEMY) {
            //contenue
        }
    }

    @Override
    public float[] getBounds() {
        return new float[] {location[0], location[1], WIDTH, HEIGHT};
    }

    @Override
    public void update(GameState state) {
    }

    @Override
    public Boolean shouldDestruct() {
        return shouldDestruct;
    }

    @Override
    public void setDestruct() {
        shouldDestruct = true;
    }
}
