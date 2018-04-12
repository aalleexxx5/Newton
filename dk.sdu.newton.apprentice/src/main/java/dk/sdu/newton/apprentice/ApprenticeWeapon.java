package dk.sdu.newton.apprentice;

import common.data.GameState;
import common.data.Weapon;

public class ApprenticeWeapon extends Weapon {
    private Apprentice apprentice;
    public ApprenticeWeapon(Apprentice apprentice){
        this.apprentice=apprentice;
    }
	
	@Override
	public void onShoot(GameState state) {
		float x=apprentice.getBounds()[0];
		float y=apprentice.getBounds()[1];
		Bullet bullet = new Bullet("filename", x, y, 10, 10);
	}
	
	@Override
	public int getCooldownInMs() {
		return 2000;
	}
	
	@Override
	public void onEquip(GameState state) {
	
	}
	
	@Override
	public void onUnEquip(GameState state) {
	
	}
}
