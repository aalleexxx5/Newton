package common.data;


import common.data.entityParts.MovingPart;
import common.services.Collidable;
import common.services.Destructable;
import common.services.Updatable;

public abstract class Projectile extends Entity implements Collidable, Updatable, Destructable {
	public static final float NORMAL_SPEED = 500f;
	
	public Projectile(ProjectileDirection direction, float speed) {
		MovingPart movement = new MovingPart(direction.getVectorXComponent()*speed,direction.getVectorYComponent()*speed);
		addEntityPart(movement);
	}
	
}
