package common.data;


import common.services.Collidable;
import common.services.Drawable;
import common.services.EntityPart;
import org.osgi.util.measurement.Unit;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Any object to be rendered on screen must be an entity.
 * Convenience class for a game object.
 */
public abstract class Entity implements Drawable {
	protected float[] location = new float[2];
	private Sprite sprite;
	
	private final ArrayList<EntityPart> entityParts = new ArrayList<>(6);
	
	/**
	 * Default implementation of the Draw functionality.
	 * Uses the location array to continually update a single sprite and return it to the caller.
	 *
	 * @return the same sprite, but with new location.
	 * @see Drawable
	 */
	public Sprite draw() {
		if (sprite == null) {
			sprite = getSprite();
		}
		sprite.x = location[0];
		sprite.y = location[1];
		return sprite;
	}
	
	/**
	 * Used by default draw implementation to obtain the initial sprite.
	 * The initial location does not matter, as it is overwritten by the default implementation.
	 * the base Draw method can be overridden, if custom functionality is desired.
	 *
	 * @return the sprite to continually draw.
	 */
	public abstract Sprite getSprite();
	
	/**
	 * Adds an {@link EntityPart} to the entity.
	 *
	 * @param part the {@link EntityPart} to add.
	 */
	protected void addEntityPart(EntityPart part) {
		entityParts.add(part);
	}
	
	/**
	 * Used to get the current location of the entity.
	 *
	 * @return a float array, with index 0 as x, and index 1 y locations.
	 */
	public float[] getLocation() {
		return location;
	}
	
	/**
	 * Used to obtain a list of entityParts contained in this Entity.
	 *
	 * @return a {@link ArrayList<EntityPart>} of all parts contained.
	 */
	public ArrayList<EntityPart> getEntityParts() {
		return entityParts;
	}
	
	/**
	 * Used to get a specific {@link EntityPart}, if available.
	 *
	 * @param partClass the class of the entityPart.
	 * @param <T>       the Resulting type of the returned EntityPart.
	 * @return An entityPart of the specified class, if any is present. {@code null} otherwise.
	 */
	public <T extends EntityPart> T getEntityPart(Class<T> partClass) {
		for (EntityPart entityPart : entityParts) {
			if (partClass.isInstance(entityPart)) {
				return partClass.cast(entityPart);
			}
		}
		return null;
	}
	
	/**
	 * Used to get bounds based on the width of the sprite from the {@link #getSprite()} function.
	 *
	 * @return the bounds as used by {@link Collidable#getBounds()}
	 */
	protected final float[] defaultBounds() {
		if (sprite == null) {
			sprite = getSprite();
			if (sprite == null || sprite.getWidth() <= 0 || sprite.getHeight() <= 0) {
				throw new Error("GetSprite method returns null or 0 width. DefaultBounds method depends on a proper sprite.");
			}
		}
		return new float[]{location[0], location[1], sprite.getWidth(), sprite.getHeight()};
	}
}
