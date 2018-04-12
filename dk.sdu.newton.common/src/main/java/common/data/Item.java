package common.data;
import common.data.entityParts.InventoryPart;
import common.services.Collidable;
import common.services.Destructable;
import common.services.EntityPart;
import common.services.Equipable;

public abstract class Item extends Entity implements Collidable, Destructable {
	protected boolean destruct = false;
	
	@Override
	public void collidesWith(Collidable source) {
		destruct = (addEquipableToEntity((Entity) source));
	}
	
	@Override
	public Enum getHostility() {
		return Hostility.ITEM;
	}
	
	@Override
	public Boolean shouldDestruct() {
		return destruct;
	}
	
	private boolean addEquipableToEntity(Entity source){
		Equipable equipable = getEquipable();
		if (equipable == null) return false;
		
		for (EntityPart entityPart : source.getEntityParts()) {
			if (entityPart instanceof InventoryPart){
				((InventoryPart) entityPart).addItem(equipable, source);
				return true;
			}
		}
		return false;
	}
	
	public abstract Equipable getEquipable();
}
