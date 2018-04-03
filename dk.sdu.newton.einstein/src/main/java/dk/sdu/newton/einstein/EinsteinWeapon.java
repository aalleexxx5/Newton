package dk.sdu.newton.einstein;

import common.data.Weapon;

public class EinsteinWeapon extends Weapon{

	private Einstein einstein;

	@Override
	public void onEquip() {
	}

	private void fire(){
		float x = einstein.getBounds()[0];
		float y = einstein.getBounds()[1];
		EinsteinBullet einsteinBullet = new EinsteinBullet(x, y, 10, 10);
	}
}
