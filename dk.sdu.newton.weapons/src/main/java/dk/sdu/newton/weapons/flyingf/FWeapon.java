package dk.sdu.newton.weapons.flyingf;

import common.data.*;

/**
 * Textbook weapon shooting the flying f bullet.
 */
public class FWeapon extends Weapon {
	private Unit container;
	
	@Override
	public Item getDroppedItem() {
		return new FlyingFItem(container.getLocation());
	}
	
	@Override
	public void onShoot(GameState state, ProjectileDirection direction) {
		state.addEntity(new FlyingF(direction, container));
	}
	
	@Override
	public int getCooldownInMs() {
		return 800;
	}
	
	@Override
	public void onEquip(Unit container) {
		this.container = container;
	}
}
