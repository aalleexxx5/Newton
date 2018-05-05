package dk.sdu.newton.weapons.emergency;

import common.data.Entity;
import common.data.GameState;
import common.data.Projectile;
import common.data.Unit;
import common.data.entityParts.MovingPart;
import common.services.Collidable;
import common.services.EntityPart;
import common.services.Equipable;
import common.services.Updatable;
import dk.sdu.newton.weapons.impact.GrowingEffect;

import java.util.ArrayList;

/**
 * An Equipable, which teleports the wielder away, when in danger.
 * The target location has some grace build into it.
 */
public class EmergencyTp implements Equipable, Updatable {
	private static final String ENTERANCE_FILENAME = "blueSpiral.png";
	private static final String EXIT_FILENAME = "bluestar.png";
	private static final float DEST_PADDING = 200f;
	private static final float PADDING = 20;
	private static final int COOLDOWN = 4000;
	private Unit container;
	private long lastUse = System.currentTimeMillis();
	private MovingPart containerMove;
	private EmergencyTpReady ready;
	
	/**
	 * Initial setup of the functionality.
	 *
	 * @param container the unit to equip with the provided functionality.
	 * @see Equipable#onEquip(Unit)
	 */
	@Override
	public void onEquip(Unit container) {
		this.container = container;
		containerMove = container.getEntityPart(MovingPart.class);
		ready = new EmergencyTpReady(container.getLocation());
		ready.setDestruct();
	}
	
	@Override
	public void onUnEquip(Unit container) {
	}
	
	/**
	 * Contains the main logic of the emergency teleport behaviour.
	 *
	 * @param state where the object is contained.
	 */
	@Override
	public void update(GameState state) {
		long now = System.currentTimeMillis();
		
		if (now > lastUse + COOLDOWN) {
			updateTpReadyEffect(state);
			if (needsToTeleport(state)) {
				PerformEmercencyTeleport(state, now);
			}
		}
	}
	
	/**
	 * Contains logic for the {@link EmergencyTpReady} effect.
	 * Effect should be showing when tp
	 * is ready to be used, and switch rooms with the player
	 *
	 * @param state
	 */
	private void updateTpReadyEffect(GameState state) {
		if (ready.shouldDestruct()) {
			// If the ready effect is not showing, but should be:
			ready = new EmergencyTpReady(container.getLocation());
			state.addEntity(ready);
		} else {
			// If the ready effect is showing, but not in the current room:
			if (state.getEntitiesByInterface(EmergencyTpReady.class).isEmpty()) {
				ready.setDestruct();
			}
		}
	}
	
	/**
	 * Determines if the wielder is in danger (about to be hit by a damaging projectile).
	 *
	 * @param state the location of the player.
	 * @return true, if the wielder is determined to be in danger.
	 */
	private boolean needsToTeleport(GameState state) {
		ArrayList<Projectile> projectiles = state.getEntitiesByInterface(Projectile.class);
		for (Projectile projectile : projectiles) {
			if (projectile.getHostility() == container.getBulletHostility()) continue;
			if (isAboutToCollide(projectile)) return true;
		}
		return false;
	}
	
	/**
	 * Resets the cool-down, finds a suitable location, and moves the player to that location.
	 *
	 * @param state the state containing the player.
	 * @param now   the current time in milliseconds.
	 */
	private void PerformEmercencyTeleport(GameState state, long now) {
		lastUse = now;
		ready.setDestruct();
		state.addEntity(entranceEffect());
		float destPadding = DEST_PADDING;
		int iterations = 0;
		do {
			iterations++;
			teleportRandomly(state);
			destPadding = destPadding - 0.125f; // To ensure no infinite loop occurs.
		} while (!isSuitableLocation(state, destPadding));
		System.out.println(destPadding + ": " + iterations);
		state.addEntity(exitEffect());
	}
	
	/**
	 * Convenience function for creating an entrance effect. Should be placed at the location the wielder left.
	 *
	 * @return a {@link GrowingEffect} placed at the current location of the wielder.
	 */
	private GrowingEffect entranceEffect() {
		return new GrowingEffect(new float[]{getCenterX(container), getCenterY(container)}, ENTERANCE_FILENAME, EmergencyTpItem.WIDTH * 2, EmergencyTpItem.HEIGHT * 2, 0.3f);
	}
	
	/**
	 * Convenience function for creating an exit effect. Should be placen at the location the wielder arrived.
	 *
	 * @return a {@link GrowingEffect} placed at the location of the wielder.
	 */
	private GrowingEffect exitEffect() {
		return new GrowingEffect(new float[]{getCenterX(container), getCenterY(container)}, EXIT_FILENAME, EmergencyTpItem.WIDTH * 2, EmergencyTpItem.HEIGHT * 2, 0.3f);
	}
	
	/**
	 * Convenience function for getting the center of a Collidable.
	 *
	 * @param container the colidable to find the center of.
	 * @return the center in the y-axis.
	 */
	private float getCenterY(Collidable container) {
		return container.getBounds()[1] + container.getBounds()[3] / 2;
	}
	
	/**
	 * Convenience function for getting the center of a Collidable.
	 *
	 * @param container the colidable to find the center of.
	 * @return the center in the x-axis.
	 */
	private float getCenterX(Collidable container) {
		return container.getBounds()[0] + container.getBounds()[2] / 2;
	}
	
	/**
	 * Used to determine if the wielder is about to collide with a projectile.
	 *
	 * @param projectle the {@link Projectile} for wich to perform the check.
	 * @return True, if the projectile is about to collide with the wielder.
	 */
	private boolean isAboutToCollide(Projectile projectle) {
		return Collidable.doesCollide(getBoundsWithPadding(container, PADDING), getBoundsWithPadding(projectle, PADDING));
	}
	
	/**
	 * Convenience function for adding a padding to the bounds of a collidable.
	 *
	 * @param coll    the collidable to add the padding to.
	 * @param padding the padding to add to the collidable in each direction.
	 * @return the resulting {@link Collidable#getBounds()} array, with added padding.
	 */
	private float[] getBoundsWithPadding(Collidable coll, float padding) {
		float[] b = coll.getBounds();
		return new float[]{b[0] - padding, b[1] - padding, b[2] + padding, b[3] + padding};
	}
	
	/**
	 * Moves the container to a random location.
	 *
	 * @param state the state containing the wielder.
	 */
	private void teleportRandomly(GameState state) {
		container.getLocation()[0] = (float) ((Math.random() * (state.getWidth() - 200)) + 100);
		container.getLocation()[1] = (float) ((Math.random() * (state.getHeight() - 200)) + 100);
	}
	
	/**
	 * Used to determine if the current location of the wielder is "Suitable"
	 * A suitable location is any location where the player will not immediately take damage.
	 *
	 * @param state          the state containing the wielder.
	 * @param arrivalPadding the amount of space must be areound the wielder for a location to be deemed suitable.
	 * @return {@code true}, if the current location is not immediately dangerous.
	 */
	private boolean isSuitableLocation(GameState state, float arrivalPadding) {
		for (Collidable collidable : state.getEntitiesByInterface(Collidable.class)) {
			if (collidable == container) continue;
			if (Collidable.doesCollide(getBoundsWithPadding(container, arrivalPadding), getBoundsWithPadding(collidable, arrivalPadding))) {
				return false;
			}
		}
		return true;
	}
}
