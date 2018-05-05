package dk.sdu.newton.weapons.homingRocket;

import common.data.Entity;
import common.data.Sprite;
import common.services.Destructable;
import dk.sdu.newton.weapons.impact.GrowingEffect;

/**
 * Creates a smoke trail after the rocket.
 * The particle is slightly transparent, and will be expanding
 * to give the impression of smoke dispersing.
 */
public class RocketParticle extends GrowingEffect {
	private static final String FILENAME = "smoke.png";
	private static final float WIDTH = 32;
	private static final float HEIGHT = 32;
	
	/**
	 * Defines an expanding smoke particle with an initial size of half the final size.
	 *
	 * @param location    the location of the smoke particle.
	 * @param duration_ms the duration of the smoke.
	 */
	public RocketParticle(float[] location, int duration_ms) {
		super(location.clone(), FILENAME, WIDTH, HEIGHT, duration_ms * 2 / 1000f);
		// Starts half done. Thus the particle is half its full size initially.
		setCurrentDuration(duration_ms / 1000f);
		noShrink();
	}
}
