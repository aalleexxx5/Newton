package common.data;

import java.util.ArrayList;
import java.util.HashMap;

public class Registrator {
	private static Registrator ourInstance = new Registrator();
	
	public static Registrator getInstance() {
		return ourInstance;
	}
	
	private HashMap<AvailableStates ,GameState> gameStates = new HashMap<>(5);
	
	private Registrator() {
	
	}
	
	public void registerGameState(AvailableStates states, GameState register){
		gameStates.put(states, register);
	}
	
	public GameState getState(AvailableStates state){
		return gameStates.get(state);
	}
}
