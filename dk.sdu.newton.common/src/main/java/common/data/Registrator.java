package common.data;

import common.data.mapParts.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Registrator {

	private static  HashMap<Entity,Integer> enemyMap = new HashMap<>();

	private static Registrator ourInstance = new Registrator();
	
	public static Registrator getInstance() {
		return ourInstance;
	}
	
	private HashMap<AvailableStates,GameState> gameStates = new HashMap<>(5);

	private static ArrayList<Room> roomsList = new ArrayList<>();
	
	private Registrator() { }
	
	public void registerGameState(AvailableStates states, GameState register){
		gameStates.put(states, register);
	}
	
	public GameState getState(AvailableStates state){
		if (gameStates.get(state) == null) {
			GameState gameState = new GameState();
			gameStates.put(state, gameState);
			return gameState;
		}
		return gameStates.get(state);
	}

	public static void registerRoom(Room room){
		roomsList.add(room);
	}

	public static void unregisterRoom(Room room){
		roomsList.clear();
	}

	public static void registerEnemy(Entity enemy,int difficulty ){
		enemyMap.put(enemy,difficulty);


	}
	public static void unregisterEnemy(Entity enemy){
		enemyMap.remove(enemy);
	}
}
