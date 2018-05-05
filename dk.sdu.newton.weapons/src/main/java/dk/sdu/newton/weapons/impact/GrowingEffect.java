package dk.sdu.newton.weapons.impact;

import common.data.*;
import common.services.Destructable;
import common.services.Drawable;
import common.services.Updatable;

/**
 * Defines an entity which linearly grows (and shrinks) over its lifetime.
 */
public class GrowingEffect extends Entity implements Destructable, Updatable {
	private final float maxDuration;
	private final float WIDTH;
	private final float HEIGHT;
	private final String FILENAME;
	private final float[] originLocation;
	private float currentDuration = 0f;
	private float currentWidth = 0;
	private float currentHeight = 0;
	private boolean shrink = true;
	
	public GrowingEffect(float[] location, String filename, float width, float height, float durationSeconds) {
		this.originLocation = location.clone();
		this.FILENAME = filename;
		this.WIDTH = width;
		this.HEIGHT = height;
		this.maxDuration = durationSeconds;
	}
	
	/**
	 * Overridden, as the width and height changes.
	 *
	 * @see Drawable#draw()
	 * @see Entity#draw()
	 */
	@Override
	public final Sprite draw() {
		return new Sprite(FILENAME, location[0], location[1], currentWidth, currentHeight);
	}
	
	/**
	 * Not used, as base draw method is overridden.
	 *
	 * @see Entity#draw()
	 */
	@Override
	public final Sprite getSprite() {
		return null;
	}
	
	/**
	 * Modifies the size and location of the entity each frame.
	 *
	 * @param state where the object is contained.
	 */
	@Override
	public final void update(GameState state) {
		currentDuration += state.getDeltaTime();
		if (shrink) {
			if (currentDuration < maxDuration / 2) {
				growUntill(maxDuration / 2);
			} else {
				shrink();
			}
		} else {
			growUntill(maxDuration);
		}
		location[0] = originLocation[0] - currentWidth / 2;
		location[1] = originLocation[1] - currentHeight / 2;
	}
	
	/**
	 * Shrinks the entity after it has grown for half the duration.
	 */
	private void shrink() {
		currentWidth = WIDTH - WIDTH * (currentDuration - maxDuration / 2) / (maxDuration / 2);
		currentHeight = HEIGHT - HEIGHT * (currentDuration - maxDuration / 2) / (maxDuration / 2);
	}
	
	/**
	 * Grows the entity reaching its full size after the specified duration.
	 *
	 * @param fullSizeDuration the duration after the entity should have reached its full size.
	 */
	private void growUntill(float fullSizeDuration) {
		currentWidth = WIDTH * (currentDuration / (fullSizeDuration));
		currentHeight = HEIGHT * (currentDuration / (fullSizeDuration));
	}
	
	/**
	 * Destructs when the duration has ended.
	 *
	 * @return {@code true}, when the entity should be removed.
	 */
	@Override
	public final Boolean shouldDestruct() {
		return currentDuration >= maxDuration;
	}
	
	/**
	 * Not used.
	 */
	@Override
	public void setDestruct() {
	
	}
	
	/**
	 * Convenience function for getting the current bounds of the entity.
	 * Used by collision detection by extending classes.
	 *
	 * @return
	 */
	protected float[] getGrowBounds() {
		return new float[]{location[0], location[1], currentWidth, currentHeight};
	}
	
	/**
	 * Setter for the currentDuration.
	 * Used for creating custom grow/shrink behavior.
	 * Care should be taken, as the entity will destruct
	 * 'when the currentDuration has exceeded the duration specified in the constructor.
	 *
	 * @param durationSeconds
	 */
	protected void setCurrentDuration(float durationSeconds) {
		currentDuration = durationSeconds;
	}
	
	/**
	 * Used to disable the shrink halfway through entity duration.
	 */
	protected void noShrink() {
		shrink = false;
	}
}