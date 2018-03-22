package common.data.entityParts;

import common.data.Entity;
import common.data.Sprite;
import common.services.EntityPart;

public class MovingPart implements EntityPart {
	float dx, dy;
	
	public MovingPart(float dx, float dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update(Entity container, float dt) {
		Sprite sprite = container.getSprite();
		sprite.setX(sprite.getX()+dx*dt);
		sprite.setY(sprite.getY()+dy*dt);
	}
	
	public void setDx(float dx) {
		this.dx = dx;
	}
	
	public void setDy(float dy) {
		this.dy = dy;
	}
}
