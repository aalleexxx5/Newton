package dk.sdu.newton.player;

import common.data.*;
import common.services.Collidable;

import static common.data.Hostility.KILLS_ENEMY;

public class AppleBullet extends Projectile {
	private boolean shouldDestruct = false;
	private Sprite sprite;
	private float dx;
	private float dy;
	private float speed = 10;
	private String filename = "AppleBullet.png";
	long startTime = System.currentTimeMillis();

	//TODO find sprite

	public AppleBullet(float x, float y, float width, float height){
		sprite = new Sprite(filename, x , y, width, height);
	}

	@Override
	public Sprite draw() {
		return sprite;
	}

	@Override
	public Sprite getSprite() {
		return sprite;
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
		float[] bounds = new float[3];
		bounds[0] = sprite.getX();
		bounds[1] = sprite.getY();
		bounds[2] = sprite.getWidth();
		bounds[3] = sprite.getHeight();
		return new float[0];
	}

	@Override
	public Boolean shouldDestruct() {
		if (System.currentTimeMillis()+5000 > startTime) {
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
