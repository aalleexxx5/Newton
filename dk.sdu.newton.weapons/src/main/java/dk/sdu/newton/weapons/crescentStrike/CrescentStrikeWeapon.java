package dk.sdu.newton.weapons.crescentStrike;

import common.data.*;

public class CrescentStrikeWeapon extends Weapon {
    Unit container;

    /**
     * makes a crescentstrikeitem in the map when it is dropped
     *
     * @return
     */
    @Override
    public Item getDroppedItem() {
        return new CrescentStrikeItem(container.getLocation());
    }

    /**
     * spawns a new bullet whenever this method is called, used to shoot enemies
     *
     * @param state     the gameState, where the shot occurred.
     * @param direction the direction, the weapon is pointing in.
     */
    @Override
    public void onShoot(GameState state, ProjectileDirection direction) {
        CrescentStrikeBullet csBullet = new CrescentStrikeBullet(direction, container);
        state.addEntity(csBullet);
    }

    /**
     * The bullet isn't shot every frame
     *
     * @return the cooldown in milli sec
     */
    @Override
    public int getCooldownInMs() {
        return 500;
    }

    /**
     * What happens when the item is equipped
     *
     * @param container the unit to equip with the provided functionality.
     */
    @Override
    public void onEquip(Unit container) {
        this.container = container;
    }
}
