package common.data;


import common.data.entityParts.MovingPart;
import common.services.Collidable;
import common.services.Destructable;
import common.services.Updatable;

/**
 * Convenience class for creating projectiles.
 * A projectile is a moving, collidable particle.
 */
public abstract class Projectile extends Entity implements Collidable, Updatable, Destructable {
	public static final float NORMAL_SPEED = 350f;
	private final Hostility hostility;
	protected final Unit origin;
	
	/**
	 * Constructor for a simple particle.
	 * @param direction used to specify the travel direction of the particle upon creation.
	 * @param speed used to specify the speed of the particle.
	 * @param origin used to specify the Unit where the particle originated from. A particle will not interact with its origin.
	 */
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
