package dk.sdu.newton.collision.internal.mocks;

import common.data.Entity;
import common.data.Hostility;
import common.data.Sprite;
import common.services.Collidable;

public class CollidingMock extends Entity implements Collidable {
	float[] bounds;
	public CollidingMock(float[] bounds) {
		this.bounds = bounds;
	}
	
	@Override
	public Sprite getSprite() {
		return null;
	}
	
	@Override
	public Hostility getHostility() {
		return Hostility.NO_EFFECT;
	}
	
	@Override
	public void collidesWith(Collidable source) {
		throw new Collision();
	}
	
	@Override
	public float[] getBounds() {
		return bounds;
	}
}
