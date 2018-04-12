package dk.sdu.newton.einstein;

import common.data.*;

public class EinsteinWeapon extends Weapon{

	private Entity container;
	
	@Override
	public void onShoot(GameState state) {
		float x = container.getLocation()[0];
		float y = container.getLocation()[1];
		state.addEntity(new EinsteinBullet(x,y, ProjectileDirection.NORTH));
	}
	
	@Override
	public int getCooldownInMs() {
		return 20000;
	}
	
	@Override
	public void onEquip(Entity container) {
		this.container = container;
	}
	
	@Override
	public void onUnEquip(Entity container) {
	
	}
}
