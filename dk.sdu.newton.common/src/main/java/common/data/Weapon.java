package common.data;


import common.services.Equipable;

public abstract class Weapon implements Equipable {
	private long lastShot = System.currentTimeMillis()-(getCooldownInMs()-initialCooldown());
	public void shoot(GameState state, ProjectileDirection direction){
		long now = System.currentTimeMillis();
		if (lastShot+ getCooldownInMs()<= now){
			lastShot = now;
			onShoot(state, direction);
		}
	}
	protected int initialCooldown(){
		return 0;
	}
	public abstract void onShoot(GameState state, ProjectileDirection direction);
	public abstract int getCooldownInMs();
}
