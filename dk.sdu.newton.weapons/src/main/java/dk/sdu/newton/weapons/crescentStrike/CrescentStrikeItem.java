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

    /**
     * used to create new entities of the crescentstrike item
     *
     * @param location location there the item should be placed
     */
    public CrescentStrikeItem(float[] location) {
        this.location = location;
    }

    /**
     * used to equip the weapon
     *
     * @return returns an object of the crescentstrikeweapon
     */
    @Override
    public Equipable getEquipable() {
        return new CrescentStrikeWeapon();
    }

    /**
     * used in conjuction with other methods to draw the image of the weapon
     *
     * @return the png, width, height of the png
     */
    @Override
    public Sprite getSprite() {
        return new Sprite(FILENAME, 0, 0, WIDTH, HEIGHT);
    }

    /**
     * used in conjunction with collision
     *
     * @return location, height, and width
     */
    @Override
    public float[] getBounds() {
        return new float[]{location[0], location[1], WIDTH, HEIGHT};
    }

    /**
     * used to remove object
     */
    @Override
    public void setDestruct() {
        setDestruct = true;
    }

}
