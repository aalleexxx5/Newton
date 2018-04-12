package dk.sdu.newton.player;

import common.data.Item;
import common.data.Sprite;
import common.services.Collidable;
import common.services.Equipable;

import static common.data.Hostility.NO_EFFECT;

public class AppleItem extends Item {
	private boolean setDestruct = false;
	private Sprite sprite;
	//TODO find sprite

	@Override
	public Sprite getSprite() {
		return sprite;
	}

	@Override
	public Enum getHostility() {
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
		return new float[0];
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
