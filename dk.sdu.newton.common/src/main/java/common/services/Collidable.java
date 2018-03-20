package common.services;

public interface Collidable {
	/**
	 * Used by other collidables to determine the hostility of this Collidable.
	 * @return the hostility of the collidable.
	 */
    public Enum getHostility();
	
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

}
