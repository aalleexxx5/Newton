package common.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Registrator {

    private static HashMap<Entity, Integer> enemyMap = new HashMap<>();

    private static Registrator ourInstance = new Registrator();

    public static Registrator getInstance() {
        return ourInstance;
    }

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

    public static void registerEnemy(Entity enemy, int difficulty) {
        enemyMap.put(enemy, difficulty);


    }

    public static void unregisterEnemy(Entity enemy) {
        enemyMap.remove(enemy);
    }

    public <K, V> K getEnemyByDifficulty(Map<K,V> enemyMap, V difficulty) {
        for (Map.Entry<K,V> entry : enemyMap.entrySet()){
            if (difficulty.equals(entry.getValue())){
                return entry.getKey();
            }
        }
        return null;
    }
}
