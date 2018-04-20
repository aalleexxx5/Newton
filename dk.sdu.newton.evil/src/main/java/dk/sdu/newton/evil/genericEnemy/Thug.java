package dk.sdu.newton.evil.genericEnemy;

import common.data.*;
import common.data.entityParts.InventoryPart;
import common.data.entityParts.LifePart;
import common.data.entityParts.MovingPart;
import common.services.Collidable;

public class Thug extends Unit {
	private static final String FILENAME = "einstein.png";
	private static final float WIDTH = 32;
	private static final float HEIGHT = 32;
	LifePart health;
	InventoryPart inventory;
	MovingPart move;
	
	public Thug(float x, float y) {
		health = new LifePart(3);
		addEntityPart(health);
		
		inventory = new InventoryPart();
		addEntityPart(inventory);
		Weapon weapon =  Registrator.getInstance().getRandomWeaponLowerThan(10);
		inventory.addItem(weapon,this);
		
		move = new MovingPart(0,0);
		addEntityPart(move);
		
		location[0] = x;
		location[1] = y;
	}
	
	@Override
	public Unit addAtLocation(int x, int y) {
		return new Thug(x,y);
	}
	
	@Override
	public Hostility getBulletHostility() {
		return Hostility.KILLS_PLAYER;
	}
	
	@Override
	public Sprite getSprite() {
		return new Sprite(FILENAME, 0,0,WIDTH, HEIGHT);
	}
	
	@Override
	public Hostility getHostility() {
		return Hostility.NO_EFFECT;
	}
	
	@Override
	public void collidesWith(Collidable source) {
		if (source.getHostility() == Hostility.KILLS_ENEMY) health.decrement();
	}
	
	@Override
	public float[] getBounds() {
		return new float[] {location[0], location[1], WIDTH, HEIGHT};
	}
	
	@Override
	public Boolean shouldDestruct() {
		return health.isDead();
	}
	
	@Override
	public void setDestruct() {
		health.decrement();
	}
	
	@Override
	public void update(GameState state) {
		inventory.shoot(state, ProjectileDirection.random());
	}
}
