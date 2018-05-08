package dk.sdu.newton.apprentice;

import common.data.Item;
import common.data.Sprite;
import common.services.Equipable;

public class ApprenticeWeaponItem extends Item {
	private static final float WIDTH = 16;
	private static final float HEIGHT = 16;
	private static final String FILENAME = "apprenticebullet.png";
	
	public ApprenticeWeaponItem(float x, float y) {
		location[0] = x;
		location[1] = y;
	}
	
	@Override
	public Equipable getEquipable() {
		return new ApprenticeWeapon();
	}
	
	@Override
	public Sprite getSprite() {
		return new Sprite(FILENAME, 0,0,WIDTH, HEIGHT);
	}
	
	@Override
	public float[] getBounds() {
		return defaultBounds();
	}
	
	@Override
	public void setDestruct() {
	
	}
}
