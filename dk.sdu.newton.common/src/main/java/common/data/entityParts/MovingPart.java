package common.data.entityParts;

import common.data.Entity;
import common.data.GameState;
import common.services.EntityPart;

/**
 * Used to move an entity every frame.
 */
public class MovingPart implements EntityPart {
	float dx, dy, lastdt;
	
	/**
	 * Creates a movingPart with a specific vector of movement.
	 *
	 * @param dx the amount of pixels per second to move in the x direction.
	 * @param dy the amount of pixels per second to move in the y direction.
	 */
	public MovingPart(float dx, float dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	/**
	 * Getter for the pixels per seconds moved in the x direction
	 *
	 * @return the amount of pixels moved per second
	 */
	public float getDx() {
		return dx;
	}
	
	/**
	 * Getter for the pixels per seconds moved in the y direction
	 *
	 * @return the amount of pixels moved per second
	 */
	public float getDy() {
		return dy;
	}
	
	@Override
	public void update(Entity container, GameState state) {
		lastdt = state.getDeltaTime();
		updateLocation(container, state.getDeltaTime());
	}
	
	/**
	 * Updates the location of the container by the specified delta x and y values.
	 *
	 * @param container the object containing the movingPart.
	 * @param dt        the delta-time.
	 */
	public void updateLocation(Entity container, float dt) {
		float[] location = container.getLocation();
		location[0] += dx * dt;
		location[1] += dy * dt;
	}
	
	/**
	 * Sets the movement to 0.
	 */
	public void halt() {
		dx = 0;
		dy = 0;
	}
	
	/**
	 * Setter for the delta-x value.
	 * @param dx the new delta-x.
	 */
	public void setDx(float dx) {
		this.dx = dx;
	}
	
	/**
	 * Setter for the delta-y value.
	 * @param dy the new delta-y.
	 */
	public void setDy(float dy) {
		this.dy = dy;
	}
	
	/**
	 * Revert location to the location at the previous frame.
	 * Used to revert to a state before a collision took place.
	 *
	 * @param container the entity to move back in time.
	 */
	public void revertToLastFrame(Entity container) {
		updateLocation(container, -lastdt);
	}
}
