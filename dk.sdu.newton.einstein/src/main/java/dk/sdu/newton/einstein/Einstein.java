package dk.sdu.newton.einstein;

import common.data.GameState;
import common.data.Projectile;
import common.data.Sprite;
import common.data.Unit;
import common.services.Collidable;

import static common.data.Hostility.PASSIVE;

public class Einstein extends Unit {
	int hp = 1000;
	boolean shouldDestuct = false;
	private Sprite sprite;
	private float dx;
	private float dy;
	private String filename = "Einstein.png";
	private EinsteinWeapon einsteinWeapon;

	//TODO find sprite

	public Einstein(float x, float y, float width, float height) {
		sprite = new Sprite(filename, x ,y, width, height);
		einsteinWeapon = new EinsteinWeapon();
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
		return PASSIVE;
	}

	@Override
	public void collidesWith(Collidable source) {
		Enum i = source.getHostility();
		if (common.data.Hostility.KILLS_ENEMY.equals(i)) {
			hp = hp - 20;
		}
	}

	@Override
	public float[] getBounds() {
		float[] bounds = new float[4];
		bounds[0] = sprite.getX();
		bounds[1] = sprite.getY();
		bounds[2] = sprite.getWidth();
		bounds[3] = sprite.getHeight();
		return bounds;
	}

	@Override
	public Boolean shouldDestruct() {
		return shouldDestuct;
	}

	@Override
	public void setDestruct() {
		if (hp <= 0 ) {
			shouldDestuct = true;
		}
	}

	@Override
	public void update(GameState state) {
		EinsteinControl einsteinControl = new EinsteinControl(this);
		sprite.setX(sprite.getX() + einsteinControl.getDX() * state.getDeltaTime()) ;
		sprite.setY(sprite.getY() + einsteinControl.getDY() * state.getDeltaTime()) ;
	}
}