package common.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Registrator {
	private static HashMap<Unit, Integer> enemyMap = new HashMap<>();
	private static HashMap<Weapon, Integer> weaponMap = new HashMap<>();
	private static Registrator ourInstance = new Registrator();
	private HashMap<AvailableStates, GameState> gameStates = new HashMap<>(5);
	
	public static Registrator getInstance() {
		return ourInstance;
	}
	
	public static void registerEnemy(Unit enemy, int difficulty) {
		enemyMap.put(enemy, difficulty);
	}
	
	public static void unregisterEnemy(Unit enemy) {
		enemyMap.remove(enemy);
	}
	
	public void registerWeapon(Weapon weapon, int difficulty) {
		weaponMap.put(weapon, difficulty);
	}
	
	public void unregisterWeapon(Weapon weapon) {
		weaponMap.remove(weapon);
	}
	
	public Weapon getRandomWeaponLowerThan(int difficulty) {
		Map.Entry<Weapon, Integer> highestLower = null;
		ArrayList<Weapon> weapons = new ArrayList<>(10);
		for (Map.Entry<Weapon, Integer> weaponIntegerEntry : weaponMap.entrySet()) {
			if (weaponIntegerEntry.getValue() <= difficulty) {
				weapons.add(weaponIntegerEntry.getKey());
			}
		}
		try {
			if (weapons.isEmpty()) {
				return null;
			} else {
				return (Weapon) weapons.get((int) (Math.random() * weapons.size())).clone();
			}
		} catch (CloneNotSupportedException e) {
			throw new Error("Failed to clone weapon: " + e);
		}
	}
	
	public Weapon getWeaponByDifficulty(int difficulty) {
		Map.Entry<Weapon, Integer> highestLower = null;
		ArrayList<Weapon> weapons = new ArrayList<>(10);
		for (Map.Entry<Weapon, Integer> weaponIntegerEntry : weaponMap.entrySet()) {
			if (weaponIntegerEntry.getValue() < difficulty) {
				if (highestLower == null) highestLower = weaponIntegerEntry;
				highestLower = weaponIntegerEntry.getValue() > highestLower.getValue() ? weaponIntegerEntry : highestLower;
			} else if (weaponIntegerEntry.getValue() == difficulty) weapons.add(weaponIntegerEntry.getKey());
		}
		try {
			if (weapons.isEmpty()) {
				return highestLower != null ? (Weapon) highestLower.getKey().clone() : null;
			} else {
				return weapons.get((int) (Math.random() * weapons.size()));
			}
		} catch (CloneNotSupportedException e) {
			throw new Error("Failed to clone weapon: " + e);
		}
	}
	
	public void registerGameState(AvailableStates states, GameState register) {
		gameStates.put(states, register);
	}
	
	public GameState getState(AvailableStates state) {
		if (gameStates.get(state) == null) {
			GameState gameState = new GameState();
			gameStates.put(state, gameState);
			return gameState;
		}
		return gameStates.get(state);
	}
	
	public Unit getEnemy(int difficulty) {
		
		for (Unit e : enemyMap.keySet()) {
			if (enemyMap.get(e).equals(difficulty)) {
				return e;
			}
		}
		return null;
	}
	
	public HashMap getHashMap() {
		return enemyMap;
	}
}
