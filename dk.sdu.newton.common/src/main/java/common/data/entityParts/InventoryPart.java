package common.data.entityParts;

import common.data.*;
import common.services.EntityPart;
import common.services.Equipable;
import common.services.Updatable;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class InventoryPart implements EntityPart {
	private ArrayList<Equipable> eqiuppedItems = new ArrayList<>(16);
	private ArrayList<Equipable> removeItems = new ArrayList<>(8);
	private ArrayList<Equipable> addItems = new ArrayList<>(8);// Changed from copyOnWrite for control over when items are added
	
	public void addItem(Equipable equipable, Entity container){
		System.out.println("Added: "+ equipable.getClass().getSimpleName());
		equipable.onEquip(container);
		addItems.add(equipable);
	}
	
	public void removeItem(Equipable equipable, Entity container){
		equipable.onUnEquip(container);
		removeItems.add(equipable);
	}
	
	@Override
	public void update(Entity container, GameState state) {
		for (Equipable eqiuppedItem : eqiuppedItems) {
			if (eqiuppedItem instanceof Updatable){
				((Updatable) eqiuppedItem).update(state);
			}
		}
		eqiuppedItems.removeAll(removeItems);
		removeItems.clear();
		eqiuppedItems.addAll(addItems);
		addItems.clear();
	}
	
	public void shoot(GameState state, ProjectileDirection direction){
		for (Equipable eqiuppedItem : eqiuppedItems) {
			if (eqiuppedItem instanceof Weapon){
				((Weapon) eqiuppedItem).shoot(state, direction);
			}
		}
	}
}
