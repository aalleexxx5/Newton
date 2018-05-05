package dk.sdu.newton.weapons.homingRocket;

import common.data.Item;
import common.data.Sprite;
import common.services.Equipable;

/**
 * Textbook weaponItem.
 *
 * @see Item
 */
public class RocketItem extends Item {
	private static final String FILENAME = "rocketlauncher.png";
	private static final float WIDTH = 32;
	private static final float HEIGHT = 32;
	
	public RocketItem(float[] location) {
		this.location = location;
	}
	
	@Override
	public Equipable getEquipable() {
		return new RocketWeapon();
	}
	
	@Override
	public Sprite getSprite() {
		return new Sprite(FILENAME, location[0], location[1], WIDTH, HEIGHT);
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
