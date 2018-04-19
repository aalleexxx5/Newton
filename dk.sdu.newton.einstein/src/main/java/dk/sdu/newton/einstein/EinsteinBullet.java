package dk.sdu.newton.einstein;

import common.data.*;
import common.data.entityParts.MovingPart;
import common.services.Collidable;

import static common.data.Hostility.KILLS_ENEMY;
import static common.data.Hostility.KILLS_PLAYER;
import static common.data.Hostility.PASSIVE;

public class EinsteinBullet extends Projectile {
	private static final int WIDTH = 16;
	private static final int HEIGHT = 16;
	private static final int DURATION_MS = 5000;
	private static final String FILENAME = "einsteinbullet.png";
	private static final float SPEED = 250f;
	
	private final long startTime = System.currentTimeMillis();
	private boolean shouldDestruct = false;


	public EinsteinBullet(float x, float y, ProjectileDirection direction, Unit origin){
		super(direction, SPEED, origin);
		location[0] = x;
		location[1] = y;
	}

	@Override
	public Sprite getSprite() {
		return new Sprite(FILENAME, 0,0,WIDTH, HEIGHT);
	}



	@Override
	public void collidesWith(Collidable source) {
		if (source == origin) return;
		if (source.getHostility() == PASSIVE) {
			setDestruct();
		} else if (source.getHostility() == KILLS_ENEMY) {
			setDestruct();
		}
	}

	@Override
	public float[] getBounds() {
		return new float[] {location[0], location[1], WIDTH, HEIGHT};
	}

	@Override
	public void update(GameState state) {
		if (location[0]>state.getWidth() || location[0]<0||location[1]>state.getHeight()||location[1]<0) {
			setDestruct();
		}
	}

	@Override
	public Boolean shouldDestruct() {
		if (System.currentTimeMillis() - DURATION_MS > startTime) {
			return true;
		}
		return shouldDestruct;
	}

	@Override
	public void setDestruct() {
		shouldDestruct = true;
	}
}
