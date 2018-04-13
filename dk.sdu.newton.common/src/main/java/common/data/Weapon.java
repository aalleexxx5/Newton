package common.data;


import common.services.Equipable;

public abstract class Weapon implements Equipable {
	private long lastShot = 0;
	public void shoot(GameState state, ProjectileDirection direction){
		long now = System.currentTimeMillis();
		if (lastShot+ getCooldownInMs()<= now){
			lastShot = now;
			onShoot(state, direction);
		}
	}
	public abstract void onShoot(GameState state, ProjectileDirection direction);
	public abstract int getCooldownInMs();
}
