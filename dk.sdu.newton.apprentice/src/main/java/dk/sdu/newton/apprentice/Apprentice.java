package dk.sdu.newton.apprentice;

import common.data.Entity;
import common.data.GameState;
import common.data.Sprite;
import common.data.Unit;
import common.data.entityParts.LifePart;
import common.data.entityParts.MovingPart;
import common.services.Collidable;

import static common.data.Hostility.*;

public class Apprentice extends Unit {
	private static final String filename = "Apprentice.png";
	private static final int WIDTH = 32;
	private static final int HEIGHT = 32;
	
	private final MovingPart movement;
	private final LifePart health;
	
	private ApprenticeControl apprenticeControl;
	
	public Apprentice(float x, float y) {
		location[0] = x;
		location[1] = y;
		apprenticeControl = new ApprenticeControl(this);
		
		movement = new MovingPart(0, 0);
		addEntityPart(movement);
		
		health = new LifePart(2);
		addEntityPart(health);
	}
	
	@Override
	public Enum getHostility() {
		return NO_EFFECT;
	}
	
	@Override
	public void collidesWith(Collidable source) {
		Enum i = source.getHostility();
		if (common.data.Hostility.PASSIVE.equals(i)) {
			movement.revertToLastFrame(this);
			movement.setDx(0);
			movement.setDy(0);
		} else if (common.data.Hostility.KILLS_ENEMY.equals(i)) {
			health.decrement();
		}
	}
	
	@Override
	public float[] getBounds() {
		return new float[] {location[0], location[1], WIDTH, HEIGHT};
	}
	
	
	@Override
	public Boolean shouldDestruct() {
		return health.getLives()<=0;
	}
	
	@Override
	public void setDestruct() {
		health.decrement();
	}
	
	
	@Override
	public void update(GameState state) {
		movement.setDx(apprenticeControl.getdx());
		movement.setDy(apprenticeControl.getdy());
		
	}
	
	
	public Sprite getSprite() {
		return new Sprite(filename, 0,0,WIDTH, HEIGHT);
	}
	
	@Override
	public Unit addAtLocation(int x, int y) {
		return new Apprentice(x, y);
	}
}
