package common.data.entityParts;

import common.data.Entity;
import common.data.GameState;
import common.services.EntityPart;

/**
 * Used to store and update any lives of an entity.
 */
public class LifePart implements EntityPart {
	int lives;
	
	@Override
	public void update(Entity container, GameState state) { }
	
	/**
	 * Creates a lifePart with the specified amount of lives.
	 * @param lives the lives to start with.
	 */
	public LifePart(int lives) {
		this.lives = lives;
	}
	
	/**
	 * Getter for the remaining lives.
	 * @return the number of lives remaining.
	 */
	public int getLives() {
		return lives;
	}
	
	/**
	 * Used as a convenience method for checking if a lifePart has run out of lives.
	 * @return true, if no lives remain.
	 */
	public boolean isDead() {
		return lives <= 0;
	}
	
	/**
	 * Decrements the amount of lives by one.
	 */
	public void decrement() {
		lives--;
	}
	
	/**
	 * Decrements the amount of lives by a specific amount.
	 * @param amount the amount of lives to remove.
	 */
	public void decrement(int amount) {
		lives -= amount;
	}
}
