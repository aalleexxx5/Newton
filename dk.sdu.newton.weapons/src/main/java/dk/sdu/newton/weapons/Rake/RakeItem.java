package dk.sdu.newton.weapons.Rake;

import common.data.Item;
import common.data.Sprite;
import common.services.Equipable;

public class RakeItem extends Item {
    private static final String FILENAME = "rakebullet.png";
    private static final float WIDTH = 16;
    private static final float HEIGHT = 16;
    private boolean setDestruct = false;

    public RakeItem(float[] location) {
        this.location = location;
    }
    @Override
    public Equipable getEquipable() {
        {
            return new RakeWeapon();
    }}

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
