package dk.sdu.newton.weapons.homingRocket;

import common.data.*;
import common.services.Collidable;

public class Rocket extends Projectile{
	private static String FILENAME = "rocket.png";
	private static final float WIDTH = 16;
	private static final float HEIGHT = 16;
	private static final float SPEED = NORMAL_SPEED*0.8f;
	private static final int DURATION_MS = 2000;
	private static final long startTime = System.currentTimeMillis();
	private boolean destruct = false;
	
	public Rocket(ProjectileDirection direction, Unit origin) {
		super(direction, SPEED, origin);
		this.location = origin.getLocation().clone();
	}
	
	@Override
	public Sprite getSprite() {
		return new Sprite(FILENAME,0,0,WIDTH, HEIGHT);
	}
	
	@Override
	public void collidesWith(Collidable source) {
		if (source == origin) return;
		if (source instanceof Projectile) return;
		setDestruct();
	}
	
	@Override
	public float[] getBounds() {
		return new float[] {location[0], location[1], WIDTH, HEIGHT};
	}
	
	@Override
	public Boolean shouldDestruct() {
		if (destruct) {
			explode();
			return true;
		}
		return false;
	}
	
	@Override
	public void setDestruct() {
		destruct = true;
	}
	
	private void explode(){
		System.out.println("POOF!");
	}
	
	@Override
	public void update(GameState state) {
	
	}
}
