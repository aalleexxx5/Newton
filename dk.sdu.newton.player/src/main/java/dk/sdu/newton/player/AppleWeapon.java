package dk.sdu.newton.player;

import common.data.*;
import common.data.entityParts.InventoryPart;
import common.services.EntityPart;

public class AppleWeapon extends Weapon {

	private Unit container;

	@Override
	public void onShoot(GameState state, ProjectileDirection direction) {
		float x = container.getLocation()[0];
		float y = container.getLocation()[1];
		AppleBullet appleBullet = new AppleBullet(x, y, direction, container.getBulletHostility());
		state.addEntity(appleBullet);
		
		for (EntityPart entityPart : container.getEntityParts()) {
			if (entityPart instanceof InventoryPart){
				((InventoryPart) entityPart).removeItem(this, container);
			}
		}
	}
	
	@Override
	public int getCooldownInMs() {
		return 200;
	}
	
	@Override
	protected int initialCooldown(){
		return 1000;
	}
	
	@Override
	public void onEquip(Unit container) {
		this.container = container;
	}
	
	@Override
	public void onUnEquip(Unit container) {
	
	}
}
