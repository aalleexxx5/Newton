package common.data;


import common.services.Equipable;

public abstract class Weapon implements Equipable {
	private long lastShot = 0;
	public void shoot(GameState state){
		long now = System.currentTimeMillis();
		if (lastShot+ getCooldownInMs()<= now){
			lastShot = now;
			onShoot(state);
		}
	}
	public abstract void onShoot(GameState state);
	public abstract int getCooldownInMs();
}
