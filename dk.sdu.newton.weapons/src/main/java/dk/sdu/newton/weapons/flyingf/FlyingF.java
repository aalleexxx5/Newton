package dk.sdu.newton.weapons.flyingf;

import common.data.*;
import common.services.Collidable;


public class FlyingF extends Projectile {
	public static final String FLYING_F_FILE = "F.png";
	public static final float WIDTH = 16;
	public static final float HEIGHT = 16;
	private boolean destruct = false;
	
	public FlyingF(ProjectileDirection direction, Unit origin) {
		super(direction, NORMAL_SPEED, origin);
		location = origin.getLocation().clone();
	}
	
	@Override
	public Sprite getSprite() {
		return new Sprite(FLYING_F_FILE, 0,0,WIDTH, HEIGHT);
	}
	
	@Override
	public void collidesWith(Collidable source) {
		if (source == origin) return;
		if (source instanceof Item) return;
		if (source instanceof Projectile) return;
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
