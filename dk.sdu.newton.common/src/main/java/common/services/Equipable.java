package common.services;

import common.data.Entity;
import common.data.GameState;
import common.data.Unit;

public interface Equipable {
	public void onEquip(Unit container);
	void onUnEquip(Unit container);
}
