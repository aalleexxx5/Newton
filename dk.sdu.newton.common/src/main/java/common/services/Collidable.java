package common.services;

import common.data.Hostility;

/**
 * Specifies the functionality required for collision.
 * This includes calculation, notification, and intent.
 * Any entity making use of this interface will be able
 * to collide with any other implementing entity.
 */
public interface Collidable {
	/**
	 * Used by other collidables to determine the hostility of this Collidable.
	 * @return the hostility of the collidable.
	 */
    public Hostility getHostility();
	
	/**
	 * Called when a collision has been detected.
	 * @param source the offending object.
	 */
	public void collidesWith(Collidable source);
	
	/**
	 * Used by collision to get the bounding box for collision checking.
	 * @return an array containing the x,y,width,height of the Collidable.
	 */
	public float[] getBounds();
	
	
	/**
	 * Used to determine if two collidables is colliding.
	 * @param aBounds A collidable.
	 * @param bBounds A different collidable.
	 * @return true, if the two collidables are overlapping.
	 */
	static boolean doesCollide(float[] aBounds, float[] bBounds) {
		return !(aBounds[0] > bBounds[0] + bBounds[2]) &&  // a.x < b.x+b.width
				!(bBounds[0] > aBounds[0] + aBounds[2]) && // b.x < a.x+a.width
				!(aBounds[1] > bBounds[1] + bBounds[3]) && // a.y < b.y+b.height
				!(bBounds[1] > aBounds[1] + aBounds[3]); } // b.y < a.y+a.height
}
