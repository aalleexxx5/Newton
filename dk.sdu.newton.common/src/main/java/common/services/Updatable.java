package common.services;

import common.data.GameState;

/**
 * Used to declare functionality to be run each frame.
 */
public interface Updatable {
	
	
	/**
	 * Called each frame. Commonly used to update variables (such as location or direction).
	 * @param state where the object is contained.
	 */
    public void update(GameState state);

}
