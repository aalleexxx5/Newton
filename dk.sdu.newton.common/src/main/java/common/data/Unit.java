package common.data;


import common.services.Collidable;
import common.services.Destructable;
import common.services.Updatable;

/**
 * Convenience class for more significant entities in the game.
 */
public abstract class Unit extends Entity implements Updatable, Collidable, Destructable {
	
	/**
	 * Used to clone a unit, and add it at a specific location.
	 * @param x the x location of the cloned unit.
	 * @param y the y location of the cloned unit.
	 * @return a new cloned unit, with a new location.
	 */
	public abstract Unit addAtLocation(int x, int y);
	
	/**
	 * Used by weapons to determine the hostility of any bullet fired by the given unit.
	 * @return the {@link Hostility} if any bullets fired by this Unit.
	 */
	public abstract Hostility getBulletHostility();
}
