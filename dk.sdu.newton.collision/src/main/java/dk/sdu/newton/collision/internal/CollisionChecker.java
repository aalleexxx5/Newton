package dk.sdu.newton.collision.internal;

import common.data.GameState;
import common.data.Registrator;
import common.services.Collidable;
import common.services.Updatable;

import java.util.ArrayList;

public class CollisionChecker implements Updatable{
	/**
	 * Called after each frame. Determines if all collidables are colliding.
	 * @param state the gameState containing the collidables.
	 */
	@Override
	public void update(GameState state) {
		ArrayList<Collidable> collidables = state.getEntitiesByInterface(Collidable.class);
		for (int i = 0; i < collidables.size(); i++) {
			for (int j = i+1; j < collidables.size(); j++) {
				if (doesCollide(collidables.get(i), collidables.get(j))){
					collidables.get(i).collidesWith(collidables.get(j));
					collidables.get(j).collidesWith(collidables.get(i));
				}
			}
		}
	}
	
	/**
	 * Used to determine if two collidables is colliding.
	 * @param a A collidable.
	 * @param b A different collidable.
	 * @return true, if the two collidables are overlapping.
	 */
	private boolean doesCollide(Collidable a, Collidable b) {
		float[] aBounds = a.getBounds();
		float[] bBounds = b.getBounds();
		return !(aBounds[0] > bBounds[0] + bBounds[2]) &&  // a.x < b.x+b.width
				!(bBounds[0] > aBounds[0] + aBounds[2]) && // b.x < a.x+a.width
				!(aBounds[1] > bBounds[1] + bBounds[3]) && // a.y < b.y+b.height
				!(bBounds[1] > aBounds[1] + aBounds[3]); } // b.y < a.y+a.height
}
