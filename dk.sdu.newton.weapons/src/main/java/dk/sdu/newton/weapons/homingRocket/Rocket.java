package dk.sdu.newton.weapons.homingRocket;

import common.data.*;
import common.data.entityParts.MovingPart;
import common.services.*;
import dk.sdu.newton.weapons.impact.Explosion;

/**
 * A rocket which flies for a short duration, then latches on to a target in pursuit.
 */
public class Rocket extends Projectile implements Updatable {
	
	private static final String FILENAME = "rocket.png";
	private static final String ACTIVE_FILENAME = "rocket_active.png";
	private static final float TARGET_SPEED_INCREASE = 2f;
	private static final float WIDTH = 16;
	private static final float HEIGHT = 16;
	private static final float SPEED = NORMAL_SPEED * 0.2f;
	private static final int DURATION_MS = 3500;
	private static final int SMOOTHING_TIME = DURATION_MS - 500;
	private static final int TARGETING_TIME = DURATION_MS / 5;
	private static final int SMOKE_SPAWN_COOLDOWN_MS = 100;
	private final long startTime = System.currentTimeMillis();
	private boolean destruct = false;
	private Unit target = null;
	private MovingPart movement;
	private ProjectileDirection direction;
	private boolean isTargetAcuired = false;
	private long lastSpawn = System.currentTimeMillis() / SMOKE_SPAWN_COOLDOWN_MS;
	
	public Rocket(ProjectileDirection direction, Unit origin) {
		super(direction, SPEED, origin);
		
		this.direction = direction;
		this.location = origin.getLocation().clone();
		movement = getEntityPart(MovingPart.class);
		
		targetClosest(origin);
	}
	
	/**
	 * Sets the closest unit as target.
	 *
	 * @param origin the unit who shot the rocket.
	 */
	private void targetClosest(Unit origin) {
		float targetDistance = 0;
		for (Unit unit : Registrator.getInstance().getState(AvailableStates.PLAY_STATE).getEntitiesByInterface(Unit.class)) {
			if (unit == origin) continue;
			if (target == null) {
				target = unit;
				targetDistance = Math.abs(target.getLocation()[0] - location[0]) + Math.abs(target.getLocation()[1] - location[1]);
			}
			float distance = Math.abs(unit.getLocation()[0] - location[0]) + Math.abs(unit.getLocation()[1] - location[1]);
			if (distance < targetDistance) {
				target = unit;
				targetDistance = distance;
			}
		}
	}
	
	/**
	 * Overridden, as the rocket turns green upon targeting.
	 *
	 * @see Drawable#draw()
	 */
	@Override
	public Sprite draw() {
		if (isTargetAcuired) {
			return new Sprite(ACTIVE_FILENAME, location[0], location[1], WIDTH, HEIGHT);
		} else {
			return new Sprite(FILENAME, location[0], location[1], WIDTH, HEIGHT);
		}
	}
	
	/**
	 * Not used, as the base draw method is overridden.
	 *
	 * @see #draw()
	 */
	@Override
	public Sprite getSprite() {
		return null;
	}
	
	/**
	 * Uses the default collision.
	 *
	 * @param source the offending object.
	 */
	@Override
	public void collidesWith(Collidable source) {
		defaultProjectileCollision(source);
	}
	
	/**
	 * Can not use the default implementation, as the {@link #getSprite()} returns null.
	 * Otherwise uses the same width and height as the sprite.
	 *
	 * @return
	 */
	@Override
	public float[] getBounds() {
		return new float[]{location[0], location[1], WIDTH, HEIGHT};
	}
	
	/**
	 * Explodes upon destruction.
	 * Destructs upon hitting something or running out of time.
	 *
	 * @return {@code true}, if the rocket should destruct.
	 */
	@Override
	public Boolean shouldDestruct() {
		if (destruct || System.currentTimeMillis() - startTime > DURATION_MS) {
			explode();
			return true;
		}
		return false;
	}
	
	/**
	 * Textbook implementation.
	 *
	 * @see Destructable#setDestruct()
	 */
	@Override
	public void setDestruct() {
		destruct = true;
	}
	
	/**
	 * Creates an explosion at the location of destruction.
	 */
	private void explode() {
		Registrator.getInstance().getState(AvailableStates.PLAY_STATE).addEntity(new Explosion(getLocation()));
	}
	
	@Override
	public void update(GameState state) {
		if (target == null) return;
		
		long currentDuration = System.currentTimeMillis() - startTime;
		if (isTargetAcuired) {
			turnTowardsTarget(currentDuration);
		} else {
			if (currentDuration > TARGETING_TIME) {
				isTargetAcuired = true;
			}
		}
	}
	
	/**
	 * Turns the rocket towards the target.
	 *
	 * @param currentDuration the time in milliseconds the rocket has been alive.
	 */
	private void turnTowardsTarget(long currentDuration) {
		createSmoke();
		float[] targetVector = calculateNormalizedTargetVector();
		
		if (currentDuration < SMOOTHING_TIME) {
			interpolateInitialVeloticy(currentDuration, targetVector);
		} else {
			flyToTarget(targetVector);
		}
	}
	
	/**
	 * Calculates and returnes a normalized vector pointing towards the target location.
	 *
	 * @return a float array containing the x and y components of the target vector.
	 */
	private float[] calculateNormalizedTargetVector() {
		float targetX = target.getLocation()[0] - location[0];
		float targetY = target.getLocation()[1] - location[1];
		float length = (float) Math.sqrt(Math.pow(targetX, 2) + Math.pow(targetY, 2));
		float div = 1 / length;
		
		return new float[]{div * targetX, div * targetY};
	}
	
	/**
	 * Performes a smooth interpolation between the original velocity, and the target velocity.
	 *
	 * @param currentDuration the time in milliseconds the rocket has been alive.
	 * @param targetVector    a normalized vector pointing towards the target location.
	 */
	private void interpolateInitialVeloticy(long currentDuration, float[] targetVector) {
		float dTime = currentDuration - TARGETING_TIME;
		float dt = dTime / (SMOOTHING_TIME);
		float rev = -dt + 1;
		
		movement.setDx((targetVector[0] * (SPEED * TARGET_SPEED_INCREASE)) * dt + (direction.getVectorXComponent() * SPEED) * rev);
		movement.setDy((targetVector[1] * (SPEED * TARGET_SPEED_INCREASE)) * dt + (direction.getVectorYComponent() * SPEED) * rev);
	}
	
	/**
	 * Flies straight towards the target location.
	 *
	 * @param targetVector a normalized vector pointing towards the target location.
	 */
	private void flyToTarget(float[] targetVector) {
		movement.setDx(targetVector[0] * SPEED * TARGET_SPEED_INCREASE);
		movement.setDy(targetVector[1] * SPEED * TARGET_SPEED_INCREASE);
	}
	
	/**
	 * periodically creates a {@link RocketParticle} entity, to give an impression of smoke.
	 */
	private void createSmoke() {
		long currentSpawn = System.currentTimeMillis() / SMOKE_SPAWN_COOLDOWN_MS;
		if (currentSpawn > lastSpawn) {
			lastSpawn = currentSpawn;
			float[] centerLocation = new float[]{location[0] + WIDTH / 2, location[1] + HEIGHT / 2};
			Registrator.getInstance().getState(AvailableStates.PLAY_STATE).addEntity(new RocketParticle(centerLocation, 1000));
		}
	}
}