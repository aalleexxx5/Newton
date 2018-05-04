package common.services;

import common.data.Entity;
import common.data.GameState;

/**
 * Specifies extra functionality to be contained in an {@link Entity}
 */
public interface EntityPart {
	/**
	 * Mimmics the {@link Updatable} functionality.
	 * The update method will be called every frame.
	 * @param container the {@link Entity} containing this EntityPart.
	 * @param state the GameState where the {@link Entity} is located.
	 */
    public void update(Entity container, GameState state);
}
