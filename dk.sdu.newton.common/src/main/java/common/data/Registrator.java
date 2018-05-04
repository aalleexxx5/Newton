package common.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Used to register and retrieve specific implementations of some game objects.
 * Provides functionality similar to the OSGi bundleContext.
 */
public class Registrator {
	private static HashMap<Unit, Integer> enemyMap = new HashMap<>();
	private static HashMap<Weapon, Integer> weaponMap = new HashMap<>();
	private static Registrator ourInstance = new Registrator();
	private HashMap<AvailableStates, GameState> gameStates = new HashMap<>(5);
	
	/**
	 * Used to get the instance of the singleton.
	 * @return the instance of the Registrator.
	 */
	public static Registrator getInstance() {
		return ourInstance;
	}
	
	/**
	 * Used to register an enemy with a specified difficulty.
	 * @param enemy the enemy to register.
	 * @param difficulty the difficulty to associate.
	 */
	public static void registerEnemy(Unit enemy, int difficulty) {
		enemyMap.put(enemy, difficulty);
	}
	
	/**
	 * Used to unregister an enemy, for when a module is unloading.
	 * @param enemy the enemy to unregister.
	 */
	public static void unregisterEnemy(Unit enemy) {
		enemyMap.remove(enemy);
	}
	
	/**
	 * Used to register a weapon with a specified difficulty.
	 * @param weapon the weapon to register.
	 * @param difficulty the difficulty of the weapon.
	 */
	public void registerWeapon(Weapon weapon, int difficulty) {
		weaponMap.put(weapon, difficulty);
	}
	
	/**
	 * Used to unregister a weapon.
	 * @param weapon the weapon to unregister.
	 */
	public void unregisterWeapon(Weapon weapon) {
		weaponMap.remove(weapon);
	}
	
	/**
	 * Used to get a random weapon with a difficulty score lower or equal to a specified one.
	 * @param difficulty the difficulty to use for the random weapon selection.
	 * @return a random {@link Weapon} with a lower or equal difficulty to the one specified.
	 */
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
	
	/**
	 * Used to get a weapon as close to, yet lower than or equal to a difficulty.
	 * @param difficulty the target difficulty.
	 * @return the matching weapon.
	 */
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
	
	/**
	 * Used to register a gameState.
	 * @param states the {@link AvailableStates} associated game state name.
	 * @param register the {@link GameState} to register.
	 */
	public void registerGameState(AvailableStates states, GameState register) {
		gameStates.put(states, register);
	}
	
	/**
	 * Used to get a specific game state.
	 * @param state the {@link AvailableStates} associated with the gameState.
	 * @return
	 */
	public GameState getState(AvailableStates state) {
		if (gameStates.get(state) == null) {
			GameState gameState = new GameState();
			gameStates.put(state, gameState);
			return gameState;
		}
		return gameStates.get(state);
	}
	
	/**
	 * Used to get an instance of an enemy, with an associated difficulty.
	 * @param difficulty the difficulty of the enemy.
	 * @return an enemy with equal difficulty to the one specified.
	 */
	public Unit getEnemy(int difficulty) {
		
		for (Unit e : enemyMap.keySet()) {
			if (enemyMap.get(e).equals(difficulty)) {
				return e;
			}
		}
		return null;
	}
}
