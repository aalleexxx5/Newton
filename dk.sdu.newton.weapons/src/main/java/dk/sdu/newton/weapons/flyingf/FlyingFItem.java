package dk.sdu.newton.weapons.flyingf;

import common.data.Item;
import common.data.Sprite;
import common.services.Equipable;

/**
 * Textbook item of the flying f weapon.
 */
public class FlyingFItem extends Item {
	public FlyingFItem(float[] location) {
		this.location = location.clone();
	}
	
	@Override
	public Equipable getEquipable() {
		return new FWeapon();
	}
	
	@Override
	public Sprite getSprite() {
		return new Sprite(FlyingF.FLYING_F_FILE, 0, 0, FlyingF.WIDTH, FlyingF.HEIGHT);
	}
	
	@Override
	public float[] getBounds() {
		return defaultBounds();
	}
	
	@Override
	public void setDestruct() {
		destruct = true;
	}
}
