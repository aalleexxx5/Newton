package dk.sdu.newton.weapons.homingRocket;

import common.data.Entity;
import common.data.Sprite;
import common.services.Destructable;

public class RocketParticle extends Entity implements Destructable {
	private static final String FILENAME = "smoke.png";
	private static final float WIDTH = 16;
	private static final float HEIGHT = 16;
	private final int duration;
	private final long startTime = System.currentTimeMillis();
	
	public RocketParticle(float[] location, int duration_ms) {
		this.location = location.clone();
		duration = duration_ms;
	}
	
	@Override
	public Sprite getSprite() {
		return new Sprite(FILENAME, 0, 0, WIDTH, HEIGHT);
	}
	
	@Override
	public Boolean shouldDestruct() {
		return System.currentTimeMillis() - startTime > duration;
	}
	
	@Override
	public void setDestruct() {
	
	}
}
