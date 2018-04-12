package common.services;

import common.data.GameState;

public interface Equipable {
	public void onEquip(GameState state);
	void onUnEquip(GameState state);
}
