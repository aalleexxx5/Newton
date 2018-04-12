package dk.sdu.newton.player;

import common.data.Entity;
import common.data.GameState;
import common.data.ProjectileDirection;
import common.data.Weapon;

public class AppleWeapon extends Weapon {

	private Player player;

	@Override
	public void onShoot(GameState state) {
		float x = player.getBounds()[0];
		float y = player.getBounds()[1];
		AppleBullet appleBullet = new AppleBullet(x, y, ProjectileDirection.NORTH);
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
