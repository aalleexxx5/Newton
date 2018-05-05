package dk.sdu.newton.weapons.flyingf;

import common.data.*;
import common.services.Collidable;

/**
 * A text-book projectile with the shape of the letter 'F' with wings.
 * A rendition of the common saying.
 */
public class FlyingF extends Projectile {
	public static final String FLYING_F_FILE = "F.png";
	public static final float WIDTH = 16;
	public static final float HEIGHT = 16;
	private boolean destruct = false;
	
	public FlyingF(ProjectileDirection direction, Unit origin) {
		super(direction, NORMAL_SPEED, origin);
		location = origin.getLocation().clone();
	}
	
	/**
	 * Textbook getSprite.
	 *
	 * @see Entity#getSprite()
	 */
	@Override
	public Sprite getSprite() {
		return new Sprite(FLYING_F_FILE, 0, 0, WIDTH, HEIGHT);
	}
	
	/**
	 * Uses the default projectile collision pattern.
	 *
	 * @param source the offending object.
	 * @see Projectile#defaultProjectileCollision(Collidable)
	 */
	@Override
	public void collidesWith(Collidable source) {
		defaultProjectileCollision(source);
	}
	
	/**
	 * Uses the default entity bounds.
	 *
	 * @see Entity#defaultBounds()
	 */
	@Override
	public float[] getBounds() {
		return defaultBounds();
	}
	
	/**
	 * Textbook destruct implementation.
	 *
	 * @see common.services.Destructable
	 */
	@Override
	public Boolean shouldDestruct() {
		return destruct;
	}
	
	/**
	 * Textbook destruct implementation.
	 *
	 * @see common.services.Destructable
	 */
	@Override
	public void setDestruct() {
		destruct = true;
	}
}
