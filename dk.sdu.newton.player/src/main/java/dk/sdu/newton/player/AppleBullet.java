package dk.sdu.newton.player;

import common.data.*;
import common.services.Collidable;

import static common.data.Hostility.KILLS_ENEMY;

public class AppleBullet extends Projectile {
	private static final float WIDTH = 16f;
	private static final float HEIGHT = 16f;
	private static final float SPEED = 300f;
	private static final String FILENAME = "AppleBullet.png";
	private static final int DURATION = 5000;
	
	private boolean shouldDestruct = false;
	private Sprite sprite;
	private float dx;
	private float dy;
	private float speed = 10;
	long startTime = System.currentTimeMillis();

	//TODO find sprite

	public AppleBullet(float x, float y, ProjectileDirection direction){
		super(direction, SPEED);
		location[0] = x;
		location[1] = y;
	}

	@Override
	public Sprite getSprite() {
		return new Sprite(FILENAME, 0,0,WIDTH, HEIGHT);
	}

	@Override
	public Enum getHostility() {
		return KILLS_ENEMY;
	}

	@Override
	public void collidesWith(Collidable source) {
		AppleItem appleItem = new AppleItem();
		Registrator.getInstance().getState(AvailableStates.PLAY_STATE).addEntity(appleItem);
		setDestruct();
	}

	@Override
	public float[] getBounds() {
		return new float[]{location[0], location[1], WIDTH, HEIGHT};
	}

	@Override
	public Boolean shouldDestruct() {
		if (System.currentTimeMillis() + DURATION > startTime) {
			return true;
		}
		return shouldDestruct;
	}

	@Override
	public void setDestruct() {
		shouldDestruct = true;
	}

	@Override
	public void update(GameState state) {
		sprite.setX(sprite.getX() + dx * state.getDeltaTime() * speed);
		sprite.setY(sprite.getY() + dy * state.getDeltaTime() * speed);
	}
}
