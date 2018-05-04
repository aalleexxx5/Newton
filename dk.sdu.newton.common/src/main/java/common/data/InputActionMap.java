package common.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

/**
 * Used for bridging input actions (ie. Key presses, mouse movements, or controller input)
 * with game specific actions, like moving the player.
 */
public class InputActionMap {
	private HashMap<String, Consumer<String>> actionMap = new HashMap<>(16);
	private HashMap<String, ArrayList<String>> keysActionMap = new HashMap<>(9);
	
	/**
	 * Used to register a game action with a callback.
	 * @param action the name of the action to register.
	 * @param callback a Consumer function to be called on action.
	 */
	public void registerAction(String action, Consumer<String> callback) {
		actionMap.put(action, callback);
	}
	
	/**
	 * Used to remove a game action.
	 * @param action the name of the action to remove.
	 */
	public void removeAction(String action) {
		actionMap.remove(action);
	}
	
	/**
	 * called by others to trigger a game action.
	 * @param action the name of the action to trigger.
	 */
	public void actionPerformed(String action) {
		Consumer<String> callback = actionMap.get(action);
		if (callback != null) {
			callback.accept(action);
		}
	}
	
	/**
	 * Used to associate a keyboard key with a game action.
	 * @param key the name of the keyboard key.
	 * @param action the name of the game action to trigger on keypress.
	 */
	public void registerKey(String key, String action) {
		if (!keysActionMap.containsKey(key)) {
			keysActionMap.put(key, new ArrayList<>(4));
		}
		keysActionMap.get(key).add(action);
	}
	
	/**
	 * Used to remove a key-action association from the input action map.
	 * @param key the name of the keyboard key.
	 * @param action the name of the action.
	 */
	public void removeKey(String key, String action) {
		keysActionMap.get(key).remove(action);
	}
	
	/**
	 * Used to obtain all registered key names.
	 * @return an {@link ArrayList<String>} of all key names registered.
	 */
	public ArrayList<String> getKeyNames() {
		ArrayList<String> keyNames = new ArrayList<>();
		keyNames.addAll(keysActionMap.keySet());
		
		return keyNames;
	}
	
	/**
	 * Called every frame, when a key is in the down position.
	 * @param key the name of the pressed key.
	 */
	public void onKeyPress(String key) {
		ArrayList<String> actions = keysActionMap.get(key);
		for (String action : actions) {
			Consumer<String> callback = actionMap.get(action);
			if (callback != null) {
				callback.accept(action);
			}
		}
	}
	
}