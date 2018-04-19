package dk.sdu.newton.player;

import common.data.*;
import common.data.entityParts.MovingPart;
import common.services.Collidable;
import common.services.EntityPart;

import java.lang.reflect.Modifier;

import static common.data.Hostility.KILLS_ENEMY;

public class AppleBullet extends Projectile {
	private static final float WIDTH = 16f;
	private static final float HEIGHT = 16f;
	private static final float SPEED = 500f;
	private static final String FILENAME = "apple.png";
	private static final int DURATION = 1000;
	private static final float DECELERATION_PR_SECOND = 1.75f;
	private boolean shouldDestruct = false;
	long startTime = System.currentTimeMillis();


	public AppleBullet(float x, float y, ProjectileDirection direction, Unit origin){
		super(direction, SPEED, origin);
		location[0] = x;
		location[1] = y;
	}

	@Override
	public Sprite getSprite() {
		return new Sprite(FILENAME, 0,0,WIDTH, HEIGHT);
	}

	@Override
	public void collidesWith(Collidable source) {
		if (!(source instanceof Player)){
			spawnItem();
		}
	}
	
	private void spawnItem() {
		if (shouldDestruct) return;
		AppleItem appleItem = new AppleItem(location[0], location[1]);
		Registrator.getInstance().getState(AvailableStates.PLAY_STATE).addEntity(appleItem);
		setDestruct();
	}
	
	@Override
	public float[] getBounds() {
		return new float[]{location[0], location[1], WIDTH, HEIGHT};
	}

	@Override
	public Boolean shouldDestruct() {
		if (shouldDestruct) return true;
		if (System.currentTimeMillis() - DURATION > startTime) {
			spawnItem();
			return true;
		}
		return false;
	}

	@Override
	public void setDestruct() {
		shouldDestruct = true;
	}

	@Override
	public void update(GameState state) {
		for (EntityPart entityPart : getEntityParts()) {
			if (entityPart instanceof MovingPart){
				MovingPart mover = (MovingPart) entityPart;
				float secondsSinceLastCall = state.getDeltaTime();
				float decelerationFactor = 1f-secondsSinceLastCall*DECELERATION_PR_SECOND;
				mover.setDx(mover.getDx()* decelerationFactor);
				mover.setDy(mover.getDy()* decelerationFactor);
			}
		}
	}
}
