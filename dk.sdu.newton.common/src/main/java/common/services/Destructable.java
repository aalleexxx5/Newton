package common.services;

/**
 * Specifies the functionality required for removing entities from the game.
 * Any implementing entity can be removed gracefully from the game.
 */
public interface Destructable {
	
	/**
	 * Returns whether the object is ready for termination.
	 * It is up to all classes containing instances of destructables
	 * to poll this method, and remove any references to this objects when true.
	 * @return {@code true}, if the object should be removed.
	 */
    public Boolean shouldDestruct();
	
	/**
	 * Used to request termination of this object.
	 * Implementing classes specifies how the request is handled.
	 */
	public void setDestruct();
}
