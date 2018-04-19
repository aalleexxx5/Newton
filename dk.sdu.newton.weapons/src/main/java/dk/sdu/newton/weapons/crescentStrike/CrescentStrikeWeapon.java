package dk.sdu.newton.weapons.crescentStrike;

import common.data.*;

public class CrescentStrikeWeapon extends Weapon{
	Unit container;
	
	@Override
	public Item getDroppedItem() {
		return new CrescentStrikeItem();
	}
	
	@Override
	public void onShoot(GameState state, ProjectileDirection direction) {
	
	}
	
	@Override
    public int getCooldownInMs() {
        return 3000;
    }
	
	@Override
	public void onEquip(Unit container) {
		this.container = container;
	}
}
