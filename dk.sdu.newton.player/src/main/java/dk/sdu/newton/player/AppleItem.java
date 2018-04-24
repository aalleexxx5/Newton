package dk.sdu.newton.player;

import common.data.Hostility;
import common.data.Item;
import common.data.Sprite;
import common.data.Weapon;
import common.services.Collidable;
import common.services.Equipable;

import static common.data.Hostility.NO_EFFECT;

public class AppleItem extends Item {
	private static final String FILENAME = "apple.png";
	private static final int WIDTH = 16;
	private static final int HEIGHT = 16;
	
	private boolean setDestruct = false;
	
	public AppleItem(float x, float y) {
		location[0] = x;
		location[1] = y;
	}
	
	@Override
	public Sprite getSprite() {
		return new Sprite(FILENAME, 0,0, WIDTH, HEIGHT);
	}

	@Override
	public Hostility getHostility() {
		return NO_EFFECT;
	}

	@Override
	public void collidesWith(Collidable source) {
		if (source instanceof Player) {
			((Player) source).addEquipable(new AppleWeapon());
			setDestruct();
		}
	}

	@Override
	public float[] getBounds() {
		return new float[] {location[0], location[1], WIDTH, HEIGHT};
	}

	@Override
	public Boolean shouldDestruct() {
		return setDestruct;
	}
	
	@Override
	public Equipable getEquipable() {
		return null;
	}
	
	@Override
	public void setDestruct() {
		setDestruct = true;
	}
}
