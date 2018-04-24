package dk.sdu.newton.weapons.homingRocket;

import common.data.*;
import common.data.entityParts.MovingPart;
import common.services.Collidable;
import common.services.EntityPart;
import dk.sdu.newton.weapons.impact.Explosion;

public class Rocket extends Projectile{
	private static String FILENAME = "rocket.png";
	private static String ACTIVE_FILENAME = "rocket_active.png";
	private static final float WIDTH = 16;
	private static final float HEIGHT = 16;
	private static final float SPEED = NORMAL_SPEED*0.2f;
	private static final int DURATION_MS = 3500;
	private static final int SMOKE_SPAWN_COOLDOWN_MS = 100;
	private final long startTime = System.currentTimeMillis();
	private boolean destruct = false;
	private Unit target = null;
	private MovingPart movement;
	private ProjectileDirection direction;
	public static final int SMOOTHING_TIME = DURATION_MS-500;
	public static final int TARGETING_TIME = DURATION_MS / 5;
	private static final float TARGET_SPEED_INCREASE = 2f;
	private boolean isTargetAcuired = false;
	private long lastSpawn = System.currentTimeMillis()/SMOKE_SPAWN_COOLDOWN_MS;
	
	public Rocket(ProjectileDirection direction, Unit origin) {
		super(direction, SPEED, origin);
		this.direction = direction;
		this.location = origin.getLocation().clone();
		
		for (EntityPart entityPart : getEntityParts()) {
			if (entityPart instanceof MovingPart) movement = (MovingPart) entityPart;
		}
		
		float targetDistance = 0;
		for (Unit unit : Registrator.getInstance().getState(AvailableStates.PLAY_STATE).getEntitiesByInterface(Unit.class)) {
			if (unit == origin) continue;
			if (target == null) {
				target = unit;
				targetDistance = Math.abs(target.getLocation()[0] - location[0]) + Math.abs(target.getLocation()[1]- location[1]);
			}
			float distance = Math.abs(unit.getLocation()[0] - location[0]) + Math.abs(unit.getLocation()[1]- location[1]);
			if (distance < targetDistance){
				target = unit;
				targetDistance = distance;
			}
		}
	}
	
	@Override
	public Sprite draw() {
		if (isTargetAcuired){
			return new Sprite(ACTIVE_FILENAME,location[0],location[1],WIDTH, HEIGHT);
		}else{
			return new Sprite(FILENAME,location[0],location[1],WIDTH, HEIGHT);
		}
	}
	
	@Override
	public Sprite getSprite() {
		return null;
	}
	
	@Override
	public void collidesWith(Collidable source) {
		if (source == origin) return;
		if (source instanceof Projectile) return;
		if (source instanceof Item) return;
		setDestruct();
	}
	
	@Override
	public float[] getBounds() {
		return new float[] {location[0], location[1], WIDTH, HEIGHT};
	}
	
	@Override
	public Boolean shouldDestruct() {
		if (destruct || System.currentTimeMillis() - startTime > DURATION_MS) {
			explode();
			return true;
		}
		return false;
	}
	
	@Override
	public void setDestruct() {
		destruct = true;
	}
	
	private void explode(){
		Registrator.getInstance().getState(AvailableStates.PLAY_STATE).addEntity(new Explosion(origin.getBulletHostility(), getLocation()));
	}
	
	@Override
	public void update(GameState state) {
		if (target == null) return;
		
		long currentDuration = System.currentTimeMillis() - startTime;
		if (isTargetAcuired){
			long currentSpawn = System.currentTimeMillis() / SMOKE_SPAWN_COOLDOWN_MS;
			if (currentSpawn > lastSpawn){
				lastSpawn = currentSpawn;
				Registrator.getInstance().getState(AvailableStates.PLAY_STATE).addEntity(new RocketParticle(location, 400));
			}
			float targetX = target.getLocation()[0] - location[0];
			float targetY = target.getLocation()[1] - location[1];
			
			float length = (float) Math.sqrt(Math.pow(targetX,2)+Math.pow(targetY,2));
			float div = 1/length;
			
			if (currentDuration < SMOOTHING_TIME){
				float dTime = currentDuration- TARGETING_TIME;
				float dt = dTime/ (SMOOTHING_TIME);
				float rev = -dt+1;
				
				movement.setDx(((div*targetX)*(SPEED*TARGET_SPEED_INCREASE))*dt+(direction.getVectorXComponent()*SPEED)*rev);
				movement.setDy(((div*targetY)*(SPEED*TARGET_SPEED_INCREASE))*dt+(direction.getVectorYComponent()*SPEED)*rev);
			}else{
				movement.setDx((div*targetX)*SPEED*TARGET_SPEED_INCREASE);
				movement.setDy((div*targetY)*SPEED*TARGET_SPEED_INCREASE);
			}
			
		}else{
			if (currentDuration > TARGETING_TIME) {
				isTargetAcuired = true;
			}
		}
	}
}