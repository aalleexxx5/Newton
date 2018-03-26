package common.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class InputActionMap {
	private HashMap<String, Consumer<String>> actionMap = new HashMap<>(16);
	private HashMap<String, ArrayList<String>> keysActionMap = new HashMap<>(9);
	
	public void registerAction(String action, Consumer<String> callback){
		actionMap.put(action, callback);
	}
	
	public void removeAction(String action){
		actionMap.remove(action);
	}
	
	public void actionPerformed(String action){
		Consumer<String> callback = actionMap.get(action);
		if (callback != null) {
			callback.accept(action);
		}
	}

	public void registerKey(String key, String action) {
		if(!keysActionMap.containsKey(key)){
			keysActionMap.put(key, new ArrayList<>(4));
		}
		keysActionMap.get(key).add(action);
	}

	public void removeKey(String key, String action) {
		keysActionMap.get(key).remove(action);
	}

    public ArrayList<String> getKeyNames() {
	    ArrayList<String> keyNames = new ArrayList<>();
        keyNames.addAll(keysActionMap.keySet());

	    return keyNames;
    }

    /**
     * Don't think this method is right?
     * @param key
     */
    public void onKeyPress(String key) {
        ArrayList<String> actions = keysActionMap.get(key);
        for (String action : actions) {
            Consumer<String> callback = actionMap.get(action);
            callback.accept(action);
        }
    }

}