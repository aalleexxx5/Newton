package dk.sdu.newton.weapons.homingRocket;

import common.data.*;

/**
 * Textbook weapon.
 *
 * @see Weapon
 */
public class RocketWeapon extends Weapon {
	private Unit container;
	
	@Override
	public Item getDroppedItem() {
		return new RocketItem(container.getLocation());
	}
	
	@Override
	public void onShoot(GameState state, ProjectileDirection direction) {
		state.addEntity(new Rocket(direction, container));
	}
	
	@Override
	public int getCooldownInMs() {
		return 5000;
	}
	
	@Override
	protected int initialCooldown() {
		return 2500;
	}
	
	@Override
	public void onEquip(Unit container) {
		this.container = container;
	}
}
