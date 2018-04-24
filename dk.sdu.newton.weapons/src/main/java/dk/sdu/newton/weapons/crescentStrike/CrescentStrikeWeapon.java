package dk.sdu.newton.weapons.crescentStrike;

import common.data.*;

public class CrescentStrikeWeapon extends Weapon{
	Unit container;
	
	@Override
	public Item getDroppedItem() {
		return new CrescentStrikeItem(container.getLocation());
	}
	
	@Override
	public void onShoot(GameState state, ProjectileDirection direction) {
		CrescentStrikeBullet csBullet = new CrescentStrikeBullet(direction, container);
		state.addEntity(csBullet);
	}
	
	@Override
    public int getCooldownInMs() {
        return 500;
    }
	
	@Override
	public void onEquip(Unit container) {
		this.container = container;
	}
}
