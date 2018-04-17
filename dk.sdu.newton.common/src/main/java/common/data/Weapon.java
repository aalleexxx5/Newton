package common.data;


import common.services.Equipable;

public abstract class Weapon implements Equipable {
	private long lastShot = System.currentTimeMillis()-(getCooldownInMs()-initialCooldown());
	private boolean hasDropped = false;
	public void shoot(GameState state, ProjectileDirection direction){
		long now = System.currentTimeMillis();
		if (lastShot+ getCooldownInMs()<= now){
			lastShot = now;
			onShoot(state, direction);
		}
	}
	
	@Override
	public final void onUnEquip(Unit container) {
		Item dropped = getDroppedItem();
		if (dropped != null && !hasDropped) {
			hasDropped = true;
			Registrator.getInstance().getState(AvailableStates.PLAY_STATE).addEntity(getDroppedItem());
		}
	}
	
	public abstract Item getDroppedItem();
	protected int initialCooldown(){
		return 0;
	}
	public abstract void onShoot(GameState state, ProjectileDirection direction);
	public abstract int getCooldownInMs();
}
