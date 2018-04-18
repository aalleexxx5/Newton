package dk.sdu.newton.weapons.crescentStrike;

import common.data.Item;
import common.data.Sprite;
import common.services.Collidable;
import common.services.Equipable;

public class CrescentStrikeItem extends Item {
    private boolean setDestruct = false;
    private Sprite sprite;

    @Override
    public Equipable getEquipable() {
        return null;
    }

    @Override
    public Sprite getSprite() {
        return null;
    }

    @Override
    public float[] getBounds() {
        return new float[0];
    }

    @Override
    public void setDestruct() {
        setDestruct = true;
    }

    public void collidesWith(Collidable source) {

    }

}
