package common.data.entityParts;

import common.data.Entity;
import common.data.Sprite;
import common.services.EntityPart;

public class MovingPart implements EntityPart {
	float dx, dy;
	private float DX;
	private float DY;

	public MovingPart(float dx, float dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update(Entity container, float dt) {
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

	public float getDX() {
		return DX;
	}

	public float getDY() {
		return DY;
	}
}
