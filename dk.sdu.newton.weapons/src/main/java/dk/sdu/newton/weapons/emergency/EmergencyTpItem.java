package dk.sdu.newton.weapons.emergency;

import common.data.Item;
import common.data.Sprite;
import common.services.Equipable;

/**
 * An item on the ground, granting the {@link EmergencyTp} equipable, on collision.
 * A very text-book implementation.
 *
 * @see Item
 */
public class EmergencyTpItem extends Item {
	public static final String FILENAME = "blueSpiral.png";
	public static float WIDTH = 32;
	public static float HEIGHT = 32;
	
	public EmergencyTpItem(float[] location) {
		this.location = location.clone();
	}
	
	@Override
	public Equipable getEquipable() {
		return new EmergencyTp();
	}
	
	@Override
	public Sprite getSprite() {
		return new Sprite(FILENAME, 0, 0, WIDTH, HEIGHT);
	}
	
	@Override
	public float[] getBounds() {
		return new float[]{location[0], location[1], WIDTH, HEIGHT};
	}
	
	@Override
	public void setDestruct() {
		destruct = true;
	}
}
