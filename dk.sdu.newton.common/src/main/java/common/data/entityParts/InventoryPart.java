package common.data.entityParts;

import common.data.*;
import common.services.EntityPart;
import common.services.Equipable;
import common.services.Updatable;

import java.util.ArrayList;

public class InventoryPart implements EntityPart {
	private ArrayList<Equipable> eqiuppedItems = new ArrayList<>(20);
	
	public void addItem(Equipable equipable, GameState state){
		equipable.onEquip(state);
		eqiuppedItems.add(equipable);
	}
	
	public void removeItem(Equipable equipable, GameState state){
		equipable.onUnEquip(state);
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
	
	public void shoot(GameState state){
		for (Equipable eqiuppedItem : eqiuppedItems) {
			if (eqiuppedItem instanceof Weapon){
				((Weapon) eqiuppedItem).shoot(state);
			}
		}
	}
}
