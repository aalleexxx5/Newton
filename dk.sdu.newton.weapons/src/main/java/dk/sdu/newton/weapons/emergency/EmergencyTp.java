package dk.sdu.newton.weapons.emergency;

import common.data.Entity;
import common.data.GameState;
import common.data.Projectile;
import common.data.Unit;
import common.data.entityParts.MovingPart;
import common.services.Collidable;
import common.services.EntityPart;
import common.services.Equipable;
import common.services.Updatable;
import dk.sdu.newton.weapons.impact.GrowingEffect;

import java.util.ArrayList;

public class EmergencyTp implements Equipable, Updatable {
	private static final float DEST_PADDING = 1000;
	private static final float PADDING = 5;
	private static final int cooldown = 0;
	private Unit container;
	private long lastUse = System.currentTimeMillis();
	private MovingPart containerMove;
	
	@Override
	public void onEquip(Unit container) {
		this.container = container;
		containerMove = getMovingPart(container);
	}
	
	@Override
	public void onUnEquip(Unit container) {
	
	}
	
	@Override
	public void update(GameState state) {
		long now = System.currentTimeMillis();
		if (now > lastUse + cooldown) {
			if (needsToTeleport(state)) {
				lastUse = now;
				state.addEntity(teleportEffect());
				float destPadding = DEST_PADDING;
				do {
					teleport(state);
					destPadding--; // To ensure no infinite loop occurs.
				} while (!isSuitableLocation(state, destPadding));
				state.addEntity(teleportEffect());
			}
		}
	}
	
	private GrowingEffect teleportEffect() {
		return new GrowingEffect(new float[]{getCenterX(container), getCenterY(container)}, EmergencyTpItem.FILENAME, EmergencyTpItem.WIDTH * 2, EmergencyTpItem.HEIGHT * 2, 0.3f);
	}
	
	private float getCenterY(Collidable container) {
		return container.getBounds()[1] + container.getBounds()[3] / 2;
	}
	
	private float getCenterX(Collidable container) {
		return container.getBounds()[0] + container.getBounds()[2] / 2;
	}
	
	private boolean needsToTeleport(GameState state) {
		ArrayList<Projectile> projectles = state.getEntitiesByInterface(Projectile.class);
		for (Projectile projectle : projectles) {
			if (projectle.getHostility() == container.getBulletHostility()) continue;
			if (isAboutToCollide(projectle)) return true;
		}
		return false;
	}
	
	private boolean isAboutToCollide(Projectile projectle) {
		MovingPart projectileMove = getMovingPart(projectle);
		
		float nextProjectileX = projectle.getBounds()[0] + ((projectileMove != null) ? projectileMove.getDx() : 0);
		float nextProjectileY = projectle.getBounds()[1] + (projectileMove != null ? projectileMove.getDx() : 0);
		
		float nextLoctaionX = container.getBounds()[0] + containerMove.getDx();
		float nextLocationY = container.getBounds()[1] + containerMove.getDy();
		
		return Collidable.doesCollide(getBoundsWithPadding(container, PADDING), getBoundsWithPadding(projectle, PADDING)) ||
				Collidable.doesCollide(
						new float[]{nextLoctaionX - PADDING, nextLocationY - PADDING, projectle.getBounds()[2] + PADDING, projectle.getBounds()[3] + PADDING},
						new float[]{nextProjectileX - PADDING, nextProjectileY - PADDING, projectle.getBounds()[2] + PADDING, projectle.getBounds()[3] + PADDING});
	}
	
	private float[] getBoundsWithPadding(Collidable coll, float padding) {
		float[] b = coll.getBounds();
		return new float[]{b[0] - padding, b[1] - padding, b[2] + padding, b[3] + padding};
	}
	
	private void teleport(GameState state) {
		container.getLocation()[0] = (float) ((Math.random() * (state.getWidth() - 200)) + 100);
		container.getLocation()[1] = (float) ((Math.random() * (state.getHeight() - 200)) + 100);
	}
	
	private MovingPart getMovingPart(Entity projectle) {
		MovingPart projectileMove = null;
		for (EntityPart entityPart : projectle.getEntityParts()) {
			if (entityPart instanceof MovingPart) projectileMove = (MovingPart) entityPart;
		}
		return projectileMove;
	}
	
	private boolean isSuitableLocation(GameState state, float arrivalPadding) {
		for (Collidable collidable : state.getEntitiesByInterface(Collidable.class)) {
			if (Collidable.doesCollide(getBoundsWithPadding(container, arrivalPadding), getBoundsWithPadding(collidable, arrivalPadding))){
				return false;
			}
		}
		return true;
	}
}
