package dk.sdu.newton.weapons.flyingf;

import common.data.Item;
import common.data.Sprite;
import common.services.Equipable;

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
		return new Sprite(FlyingF.FLYING_F_FILE, 0,0,FlyingF.WIDTH, FlyingF.HEIGHT);
	}
	
	@Override
	public float[] getBounds() {
		return new float[] {location[0], location[1], FlyingF.WIDTH, FlyingF.HEIGHT};
	}
	
	@Override
	public void setDestruct() {
		destruct = true;
	}
}
