package common.services;

import common.data.Entity;
import common.data.GameState;
import common.data.Unit;

/**
 * Used to specify objects which can be equipped by entities.
 * Equipables usually manifests as weapons or items,
 * which affect the behaviour or abilities of the containing {@link Unit}
 * @see common.data.entityParts.InventoryPart
 */
public interface Equipable {
	/**
	 * Called when the object is equipped on a Unit.
	 * Often used for dependency injection for continual monitoring.
	 * @param container the unit to equip with the provided functionality.
	 */
	public void onEquip(Unit container);
	
	/**
	 * Called when the object is removed from the containing Unit.
	 * All added functionality should be terminated.
	 * Special behaviour (Like dropping as an item on the ground) should also be handled here.
	 * @param container the unit containing this equipable. For convenience if dependency injection was not used.
	 */
	void onUnEquip(Unit container);
}
