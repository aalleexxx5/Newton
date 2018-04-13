package dk.sdu.newton.apprentice;

import common.data.Entity;
import common.data.GameState;
import common.data.ProjectileDirection;
import common.data.Weapon;

public class ApprenticeWeapon extends Weapon {
    private Entity container;
	
	@Override
	public void onShoot(GameState state, ProjectileDirection direction) {
		Bullet bullet = new Bullet(container.getLocation()[0], container.getLocation()[1],direction);
		state.addEntity(bullet);
	}
	
	@Override
	public int getCooldownInMs() {
		return 2000;
	}
	
	@Override
	public void onEquip(Entity container) {
		this.container = container;
	}
	
	@Override
	public void onUnEquip(Entity container) {
	
	}
}
