package common.data.entityParts;

import common.data.Entity;
import common.data.GameState;
import common.data.Sprite;
import common.services.EntityPart;

public class MovingPart implements EntityPart {
	float dx, dy, lastdt;
	
	public MovingPart(float dx, float dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	@Override
	public void update(Entity container, GameState state) {
		lastdt = state.getDeltaTime();
		updateLocation(container, state.getDeltaTime());
	}
	
	public void updateLocation(Entity container, float dt) {
		float[] location = container.getLocation();
		location[0]+=dx*dt;
		location[1]+=dy*dt;
	}
	
	public void setDx(float dx) {
		this.dx = dx;
	}
	
	public void setDy(float dy) {
		this.dy = dy;
	}
	
	/**
	 * Revert location to the location at the previous frame.
	 * Used to revert to a state before a collision took place.
	 * @param container the entity to move back in time.
	 */
	public void revertToLastFrame(Entity container) {
		updateLocation(container, -lastdt);
	}
}
