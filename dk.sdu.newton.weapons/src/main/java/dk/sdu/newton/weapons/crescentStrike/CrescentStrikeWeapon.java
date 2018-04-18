package dk.sdu.newton.weapons.crescentStrike;

import common.data.Entity;
import common.data.GameState;
import common.data.Weapon;

public class CrescentStrikeWeapon extends Weapon{
    @Override
    public void onShoot(GameState state) {

    }

    @Override
    public int getCooldownInMs() {
        return 0;
    }

    @Override
    public void onEquip(Entity container) {

    }

    @Override
    public void onUnEquip(Entity container) {

    }
}
