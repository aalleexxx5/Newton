package dk.sdu.newton.apprentice;

import common.data.*;

public class ApprenticeWeapon extends Weapon {
    private Unit container;
	
	@Override
	public void onShoot(GameState state, ProjectileDirection direction) {
		Bullet bullet = new Bullet(container.getLocation()[0], container.getLocation()[1],ProjectileDirection.random(), container);
		state.addEntity(bullet);
	}
	
	@Override
	public int getCooldownInMs() {
		return 1000000000+(int)(Math.random()*2500-250);
	}
	
	@Override
	public void onEquip(Unit container) {
		this.container = container;
	}
	
	
	@Override
	public Item getDroppedItem() {
		return new ApprenticeWeaponItem(container.getLocation()[0], container.getLocation()[1]);
	}
}
