package dk.sdu.newton.weapons.impact;

import common.data.*;
import common.services.Collidable;
import common.services.Destructable;
import common.services.Updatable;

import java.util.HashSet;

/**
 * Defines an explosion, damaging all units caught in the blast for 1 health.
 */
public class Explosion extends GrowingEffect implements Collidable {
	private static final float maxDuration = 0.8f;
	private static final float WIDTH = 100;
	private static final float HEIGHT = 100;
	private static final String FILENAME = "explosion.png";
	private final HashSet<Collidable> damaged = new HashSet<>();
	
	public Explosion(float[] location) {
		super(location, FILENAME, WIDTH, HEIGHT, maxDuration);
	}
	
	/**
	 * No conventional damage, as this will damage all caught in the blast each frame.
	 */
	@Override
	public Hostility getHostility() {
		return Hostility.NO_EFFECT;
	}
	
	/**
	 * Will damage any units using the {@link Destructable#setDestruct()} method.
	 * Any units damaged, will only be damaged this way once.
	 *
	 * @param source the offending object.
	 */
	@Override
	public void collidesWith(Collidable source) {
		if (damaged.contains(source)) return;
		if (source instanceof Unit) {
			((Unit) source).setDestruct();
		}
		damaged.add(source);
	}
	
	@Override
	public float[] getBounds() {
		return getGrowBounds();
	}
}
