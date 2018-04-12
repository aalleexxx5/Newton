package dk.sdu.newton.player;

import common.data.GameState;
import common.data.Weapon;

public class AppleWeapon extends Weapon {

	private Player player;

	@Override
	public void shoot(GameState state) {
		float x = player.getBounds()[0];
		float y = player.getBounds()[1];
		AppleBullet appleBullet = new AppleBullet(x, y, 10, 10);
	}

	@Override
	public void onEquip(GameState state) {

	}

	@Override
	public void onUnEquip(GameState state) {

	}
}
