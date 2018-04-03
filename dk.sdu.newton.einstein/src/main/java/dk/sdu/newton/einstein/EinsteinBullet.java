package dk.sdu.newton.einstein;

import common.data.GameState;
import common.data.Projectile;
import common.data.Sprite;
import common.services.Collidable;

import static common.data.Hostility.KILLS_PLAYER;

public class EinsteinBullet extends Projectile {

	private boolean shouldDestruct = false;
	private Sprite sprite;
	private float dx;
	private float dy;
	private float speed = 10;
	private String filename = "EinsteinBullet.png";
	long startTime = System.currentTimeMillis();

	//TODO find sprite

	public EinsteinBullet(float x, float y, float width, float height){
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
		return KILLS_PLAYER;
	}

	@Override
	public void collidesWith(Collidable source) {
		Enum i = source.getHostility();
		if (common.data.Hostility.PASSIVE.equals(i)) {
			setDestruct();
		} else if (common.data.Hostility.KILLS_PLAYER.equals(i)) {
			setDestruct();
		}
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
	public void update(GameState state) {
		sprite.setX(sprite.getX() + dx * state.getDeltaTime()*speed) ;
		sprite.setY(sprite.getY() + dy * state.getDeltaTime()*speed) ;
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
}
