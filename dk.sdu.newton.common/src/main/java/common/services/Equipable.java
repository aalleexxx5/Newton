package common.services;

import common.data.Entity;
import common.data.GameState;

public interface Equipable {
	public void onEquip(Entity container);
	void onUnEquip(Entity container);
}
