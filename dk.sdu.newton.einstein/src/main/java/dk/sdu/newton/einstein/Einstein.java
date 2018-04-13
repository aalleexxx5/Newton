package dk.sdu.newton.einstein;

import common.data.*;
import common.data.entityParts.InventoryPart;
import common.data.entityParts.LifePart;
import common.data.entityParts.MovingPart;
import common.services.Collidable;

import static common.data.Hostility.NO_EFFECT;
import static common.data.Hostility.PASSIVE;

public class Einstein extends Unit {
	private static final String FILENAME = "Einstein.png";
	private static final int WIDTH = 32;
	private static final int HEIGHT = 32;
	
	private final LifePart lives;
	private MovingPart movingPart;
	private InventoryPart inventory;
	private EinsteinControl einsteinControl;
	//TODO find sprite

	public Einstein(float x, float y) {
		location[0]=x;
		location[1]=y;
		einsteinControl = new EinsteinControl(this);
		
		lives = new LifePart(10);
		addEntityPart(lives);
		
		movingPart = new MovingPart(0, 0);
		addEntityPart(movingPart);
		
		inventory = new InventoryPart();
		addEntityPart(inventory);
		inventory.addItem(new EinsteinWeapon(),this);
	}

	@Override
	public Sprite getSprite() {
		return new Sprite(FILENAME,0,0,WIDTH, HEIGHT);
	}

	@Override
	public Enum getHostility() {
		return NO_EFFECT;
	}

	@Override
	public void collidesWith(Collidable source) {
		Enum i = source.getHostility();
		if (common.data.Hostility.KILLS_ENEMY.equals(i)) {
			lives.decrement();
		}
		else if (common.data.Hostility.PASSIVE.equals(i)) {
			movingPart.revertToLastFrame(this);
			movingPart.halt();
		}
	}

	@Override
	public float[] getBounds() {
		return new float[] {location[0], location[1], WIDTH, HEIGHT};
	}

	@Override
	public Boolean shouldDestruct() {
		return lives.isDead();
	}

	@Override
	public void setDestruct() {
		lives.decrement();
	}

	@Override
	public void update(GameState state) {
		movingPart.setDx(einsteinControl.getDX());
		movingPart.setDy(einsteinControl.getDY());
		inventory.shoot(Registrator.getInstance().getState(AvailableStates.PLAY_STATE),ProjectileDirection.WEST);
	}
	
	@Override
	public Unit addAtLocation(int x, int y) {
		return new Einstein(x,y);
	}
}
