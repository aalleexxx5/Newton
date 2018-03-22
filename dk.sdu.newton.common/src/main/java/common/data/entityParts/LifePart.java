package common.data.entityParts;

import common.data.Entity;
import common.services.EntityPart;

public class LifePart implements EntityPart {
	int lives;
	
	public LifePart(int lives) {
		this.lives = lives;
	}
	
	public void update(Entity container, float dt) {
	
	}
	
	public int getLives() {
		return lives;
	}
}
