package common.data;
import common.data.entityParts.InventoryPart;
import common.services.Collidable;
import common.services.Destructable;
import common.services.EntityPart;
import common.services.Equipable;

/**
 * Convenience class for items lying on the ground.
 * An item will provide an {@link Equipable} to any compatible {@link Unit} upon collision.
 */
public abstract class Item extends Entity implements Collidable, Destructable {
	protected boolean destruct = false;
	
	@Override
	public void collidesWith(Collidable source) {
		if (source instanceof Unit){
			destruct = (addEquipableToUnit((Unit) source));
		}
	}
	
	@Override
	public Hostility getHostility() {
		return Hostility.ITEM;
	}
	
	@Override
	public Boolean shouldDestruct() {
		return destruct;
	}
	
	/**
	 * Adds the {@link Equipable} returned from the {@link #getEquipable()} function
	 * to the unit, if compatible.
	 * @param source the unit to equip.
	 * @return true, if an equipable has been added to the unit inventory.
	 */
	private boolean addEquipableToUnit(Unit source){
		Equipable equipable = getEquipable();
		if (equipable == null) return false;
		
		InventoryPart sourceInventory = source.getEntityPart(InventoryPart.class);
		if (sourceInventory == null) {
			return false;
		}else {
			sourceInventory.addItem(equipable,source);
			return true;
		}
	}
	
	/**
	 * Used to get an equipable to add to a colliding {@link Unit}
	 * May return null.
	 * @return an {@link Equipable} to insert into a colliding units inventory.
	 */
	public abstract Equipable getEquipable();
}
