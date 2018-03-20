package common.data;

import java.util.HashMap;
import java.util.function.Consumer;

public class InputActionMap {
	private HashMap<String, Consumer<String>> actionMap = new HashMap<>(16);
	
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
}
