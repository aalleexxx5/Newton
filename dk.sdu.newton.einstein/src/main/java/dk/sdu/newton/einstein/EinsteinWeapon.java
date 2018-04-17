package dk.sdu.newton.einstein;

import common.data.*;

public class EinsteinWeapon extends Weapon{

	private Unit container;
	
	@Override
	public void onShoot(GameState state, ProjectileDirection direction) {
		float x = container.getLocation()[0];
		float y = container.getLocation()[1];
		state.addEntity(new EinsteinBullet(x,y, direction, container));
	}
	
	@Override
	public int getCooldownInMs() {
		return 200;
	}
	
	@Override
	public void onEquip(Unit container) {
		this.container = container;
	}
	
	@Override
	public Item getDroppedItem() {
		return null;
	}
}
