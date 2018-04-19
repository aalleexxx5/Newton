package dk.sdu.newton.weapons.crescentStrike;

import common.data.*;
import common.services.Collidable;

public class CrescentStrikeBullet extends Projectile {
	private static final float SPEED = 400f;
	private static final String FILENAME = "crescent.png";
	private static final float WIDTH = 16;
	private static final float HEIGHT = 16;
	private final Unit parent;
	private boolean destruct = false;
	
	public CrescentStrikeBullet(ProjectileDirection direction, Unit unit) {
    	super(direction, SPEED, unit);
    	location = unit.getLocation();
    	parent = unit;
    }

    @Override
    public Sprite getSprite() {
        return new Sprite(FILENAME, 0,0,WIDTH, HEIGHT);
    }

    @Override
    public Enum getHostility() {
        return parent.getBulletHostility();
    }

    @Override
    public void collidesWith(Collidable source) {
		if (source == parent) return;
		setDestruct();
    }

    @Override
    public float[] getBounds() {
        return new float[] {location[0], location[1], WIDTH, HEIGHT};
    }

    @Override
    public Boolean shouldDestruct() {
        return destruct;
    }

    @Override
    public void setDestruct() {
		destruct = true;
    }

    @Override
    public void update(GameState state) {

    }
}
