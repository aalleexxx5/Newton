package common.data;

import common.data.mapParts.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Registrator {
	private static Registrator ourInstance = new Registrator();
	
	public static Registrator getInstance() {
		return ourInstance;
	}
	
	private HashMap<AvailableStates ,GameState> gameStates = new HashMap<>(5);

	private static ArrayList<Room> roomsList = new ArrayList<>();
	
	private Registrator() {
	
	}
	
	public void registerGameState(AvailableStates states, GameState register){
		gameStates.put(states, register);
	}
	
	public GameState getState(AvailableStates state){
		return gameStates.get(state);
	}

	public static void registerRoom(Room room){
		roomsList.add(room);
	}

	public static void unregisterRoom(Room room){
		roomsList.clear();
	}


}
