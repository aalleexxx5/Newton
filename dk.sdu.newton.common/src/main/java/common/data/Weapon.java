package common.data;


import common.services.Equipable;

/**
 * Convenience class for creating weapons.
 */
public abstract class Weapon implements Equipable, Cloneable {
	private long lastShot = System.currentTimeMillis() - (getCooldownInMs() - initialCooldown());
	private boolean hasDropped = false;
	
	/**
	 * Called when a containing entity wishes the weapon to be fired.
	 * Will only successfully shoot, when the {@link #getCooldownInMs()} time has passed since last shot.
	 * @param state the gameState to shoot a projectile into.
	 * @param direction the direction to shoot in.
	 */
	public void shoot(GameState state, ProjectileDirection direction) {
		long now = System.currentTimeMillis();
		if (lastShot + getCooldownInMs() <= now) {
			lastShot = now;
			onShoot(state, direction);
		}
	}
	
	/**
	 * Drops the weapon as an item, if {@link #getDroppedItem()} does not return null.
	 */
	@Override
	public final void onUnEquip(Unit container) {
		Item dropped = getDroppedItem();
		if (dropped != null && !hasDropped) {
			hasDropped = true;
			Registrator.getInstance().getState(AvailableStates.PLAY_STATE).addEntity(getDroppedItem());
		}
	}
	
	/**
	 * Used to specify an item to be dropped, when the weapon is unequipped. May be null.
	 * @return an item to drop, if the weapon is dropped as an item.
	 */
	public abstract Item getDroppedItem();
	
	/**
	 * The cool-down time after a weapon is equipped.
	 * @return a cool-down time in milliseconds.
	 */
	protected int initialCooldown() {
		return 0;
	}
	
	/**
	 * Called when the weapon is fired.
	 * Should create a projectile, or other effect.
	 * @param state the gameState, where the shot occurred.
	 * @param direction the direction, the weapon is pointing in.
	 */
	public abstract void onShoot(GameState state, ProjectileDirection direction);
	
	/**
	 * Used to get the cool-down time in milliseconds.
	 * @return the time between shots, in milliseconds.
	 */
	public abstract int getCooldownInMs();
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
