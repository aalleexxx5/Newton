package dk.sdu.newton.weapons.crescentStrike;

import common.data.Item;
import common.data.Sprite;
import common.data.Weapon;
import common.services.Collidable;
import common.services.Equipable;

public class CrescentStrikeItem extends Item {
	private static final String FILENAME = "crescentmoon.png";
	private static final float WIDTH = 16;
	private static final float HEIGHT = 16;
	private boolean setDestruct = false;

    public CrescentStrikeItem(float[] location) {
        this.location = location;
    }

    @Override
    public Equipable getEquipable() {
        return new CrescentStrikeWeapon();
    }

    @Override
    public Sprite getSprite() {
        return new Sprite(FILENAME, 0,0,WIDTH, HEIGHT);
    }

    @Override
    public float[] getBounds() {
        return new float[]{location[0], location[1], WIDTH, HEIGHT};
    }

    @Override
    public void setDestruct() {
        setDestruct = true;
    }

}
