package dk.sdu.newton.einstein;

import common.data.GameState;
import common.data.Weapon;

public class EinsteinWeapon extends Weapon{

	private Einstein einstein;
	
	@Override
	public void onShoot(GameState state) {
		float x = einstein.getBounds()[0];
		float y = einstein.getBounds()[1];
		EinsteinBullet einsteinBullet = new EinsteinBullet(x, y, 10, 10);
	}
	
	@Override
	public int getCooldownInMs() {
		return 200;
	}
	
	@Override
	public void onEquip(GameState state) {
	
	}
	
	@Override
	public void onUnEquip(GameState state) {
	
	}
}
