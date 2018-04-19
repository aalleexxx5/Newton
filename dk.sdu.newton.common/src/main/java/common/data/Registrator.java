package common.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Registrator {

    private static HashMap<Unit, Integer> enemyMap = new HashMap<>();

    private static Registrator ourInstance = new Registrator();

    public static Registrator getInstance() {
        return ourInstance;
    }

    private static ArrayList<Entity> enemyList = new ArrayList<>();

    private HashMap<AvailableStates, GameState> gameStates = new HashMap<>(5);

    private Registrator() {

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

    public static void registerEnemy(Unit enemy, int difficulty) {
        enemyMap.put(enemy, difficulty);


    }

    public static void unregisterEnemy(Unit enemy) {
        enemyMap.remove(enemy);
    }

    public ArrayList<Entity> getEnemy(int difficulty){
        enemyList.clear();
        for (Entity e: enemyMap.keySet()){
            if (enemyMap.get(e).equals(difficulty)){
                enemyList.add(e);
            }
        }
        return enemyList;
    }



    public HashMap getHashMap(){
        return enemyMap;
    }
}
