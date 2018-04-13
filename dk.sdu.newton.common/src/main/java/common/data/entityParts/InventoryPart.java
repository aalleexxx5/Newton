package common.data.entityParts;

import common.data.*;
import common.services.EntityPart;
import common.services.Equipable;
import common.services.Updatable;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class InventoryPart implements EntityPart {
	private CopyOnWriteArrayList<Equipable> eqiuppedItems = new CopyOnWriteArrayList<>();
	
	public void addItem(Equipable equipable, Entity container){
		equipable.onEquip(container);
		eqiuppedItems.add(equipable);
	}
	
	public void removeItem(Equipable equipable, Entity container){
		equipable.onUnEquip(container);
		eqiuppedItems.remove(equipable);
	}
	
	@Override
	public void update(Entity container, GameState state) {
		for (Equipable eqiuppedItem : eqiuppedItems) {
			if (eqiuppedItem instanceof Updatable){
				((Updatable) eqiuppedItem).update(state);
			}
			
		}
	}
	
	public void shoot(GameState state, ProjectileDirection direction){
		for (Equipable eqiuppedItem : eqiuppedItems) {
			if (eqiuppedItem instanceof Weapon){
				((Weapon) eqiuppedItem).shoot(state, direction);
			}
		}
	}
}
