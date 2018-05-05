package dk.sdu.newton.apprentice;

import common.data.*;
import common.services.Collidable;
import common.services.Updatable;

import static common.data.Hostility.*;

public class Bullet extends Projectile {
    private static final String FILENAME = "apprenticebullet.png";
    private static final int WIDTH = 16;
    private static final int HEIGHT = 16;
    private static final float SPEED = 250f;
	private boolean shouldDestruct = false;
	private final Unit origin;
    
    public Bullet(float x, float y, ProjectileDirection direction, Unit origin){
	    super(direction, SPEED, origin);
	    this.origin = origin;
	    location[0] = x;
	    location[1] = y;
    }
    
    @Override
    public Sprite getSprite() {
        return new Sprite(FILENAME, 0,0,WIDTH, HEIGHT);
    }

    @Override
    public void collidesWith(Collidable source) {
        defaultProjectileCollision(source);
    }

    @Override
    public float[] getBounds() {
        return defaultBounds();
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
