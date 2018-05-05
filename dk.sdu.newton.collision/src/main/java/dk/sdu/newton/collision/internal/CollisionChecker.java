package dk.sdu.newton.collision.internal;

import common.data.GameState;
import common.data.Registrator;
import common.services.Collidable;
import common.services.Updatable;

import java.util.ArrayList;

public class CollisionChecker implements Updatable{
	/**
	 * Ckecks collision between collidables each frame.
	 * @param state where the object is contained.
	 * @see Updatable
	 */
	@Override
	public void update(GameState state) {
		ArrayList<Collidable> collidables = state.getEntitiesByInterface(Collidable.class);
		for (int i = 0; i < collidables.size(); i++) {
			for (int j = i+1; j < collidables.size(); j++) {
				if (Collidable.doesCollide(collidables.get(i).getBounds(), collidables.get(j).getBounds())){
					collidables.get(i).collidesWith(collidables.get(j));
					collidables.get(j).collidesWith(collidables.get(i));
				}
			}
		}
	}
}
