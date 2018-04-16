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
	
	/**
	 * Adds item to inventory and injects container. Item may be null.
	 * @param equipable the equipable to add. If null, nothing will be added.
	 * @param container the container to inject in the added equipable.
	 */
	public void addItem(Equipable equipable, Entity container){
		if (equipable == null) {
			return;
		}
		System.out.println("Added: "+ equipable.getClass().getSimpleName());
		equipable.onEquip(container);
		addItems.add(equipable);
	}
	
	/**
	 * Removes an equipable from the inventory.
	 * @param equipable the equipable to remove. May be null.
	 * @param container the Entity containing the item. Used in the onUnEquip function.
	 */
	public void removeItem(Equipable equipable, Entity container){
		if (equipable == null) {
			return;
		}
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
	
	/**
	 * Shoots all weapons in inventory, if they are not on cooldown.
	 * @param state the gameState to shoot in.
	 * @param direction the direction to shoot in.
	 */
	public void shoot(GameState state, ProjectileDirection direction){
		for (Equipable eqiuppedItem : eqiuppedItems) {
			if (eqiuppedItem instanceof Weapon){
				((Weapon) eqiuppedItem).shoot(state, direction);
			}
		}
	}
}
