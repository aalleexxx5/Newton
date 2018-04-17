package dk.sdu.newton.apprentice;

import common.data.*;

public class ApprenticeWeapon extends Weapon {
    private Unit container;
	
	@Override
	public void onShoot(GameState state, ProjectileDirection direction) {
		Bullet bullet = new Bullet(container.getLocation()[0], container.getLocation()[1],direction, container.getBulletHostility());
		state.addEntity(bullet);
	}
	
	@Override
	public int getCooldownInMs() {
		return 2000;
	}
	
	@Override
	public void onEquip(Unit container) {
		this.container = container;
	}
	
	@Override
	public void onUnEquip(Unit container) {
	
	}
}
