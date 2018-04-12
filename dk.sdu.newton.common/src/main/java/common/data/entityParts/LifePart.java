package common.data.entityParts;

import common.data.Entity;
import common.data.GameState;
import common.services.EntityPart;

public class LifePart implements EntityPart {
	int lives;
	
	@Override
	public void update(Entity container, GameState state) {
	
	}
	
	public LifePart(int lives) {
		this.lives = lives;
	}
	
	public int getLives() {
		return lives;
	}
	
	public boolean isDead(){
		return lives <= 0;
	}
	
	public void decrement(){
		lives--;
	}
	
	public void decrement(int amount){
		lives -= amount;
	}
}
