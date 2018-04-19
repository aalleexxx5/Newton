package common.data;


import common.data.entityParts.MovingPart;
import common.services.Collidable;
import common.services.Destructable;
import common.services.Updatable;

public abstract class Projectile extends Entity implements Collidable, Updatable, Destructable {
	public static final float NORMAL_SPEED = 350f;
	private final Hostility hostility;
	protected final Unit origin;
	
	public Projectile(ProjectileDirection direction, float speed, Unit origin) {
		this.origin = origin;
		this.hostility = origin.getBulletHostility();
		MovingPart movement = new MovingPart(direction.getVectorXComponent()*speed,direction.getVectorYComponent()*speed);
		addEntityPart(movement);
	}
	
	@Override
	public Hostility getHostility() {
		return hostility;
	}
}
