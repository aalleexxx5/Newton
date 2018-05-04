package common.data;

/**
 * Non OSGi dependant sprite.
 * Contains the data needed to draw an image at a location, with a size (will scale image to fit size).
 * <p>
 * More advanced drawing operation (such as rotation, cropping and masking) not available
 */
public class Sprite {
	String filename;
	float x, y, width, height;
	
	/**
	 * Creates a sprite with an image file, at a location, with a scale.
	 *
	 * @param filename the filename of the image file. All files must be in the assets folder.
	 * @param x        the x location of the sprite.
	 * @param y        the y location of the sprite.
	 * @param width    the width of the sprite.
	 * @param height   the height of the sprite.
	 */
	public Sprite(String filename, float x, float y, float width, float height) {
		this.filename = filename;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Getter for the x location of the sprite.
	 * @return a float, containing the x location of the sprite.
	 */
	public float getX() {
		return x;
	}
	
	/**
	 * Setter for the x location of the sprite.
	 * @param x the new x location.
	 */
	public void setX(float x) {
		this.x = x;
	}
	
	/**
	 * Getter for the y location of the sprite.
	 * @return a float, containing the y location of the sprite.
	 */
	public float getY() {
		return y;
	}
	
	/**
	 * Setter for the y location of the sprite.
	 * @param y the new y location of the sprite.
	 */
	public void setY(float y) {
		this.y = y;
	}
	
	/**
	 * Getter for the height of the sprite.
	 * @return a float value representing the height of the sprite.
	 */
	public float getHeight() {
		return height;
	}
	
	/**
	 * Setter for the height of the sprite.
	 * @param height the new height of the sprite.
	 */
	public void setHeight(float height) {
		this.height = height;
	}
	
	/**
	 * Getter for the width of the sprite.
	 * @return a float value representing the width of the sprite.
	 */
	public float getWidth() {
		return width;
	}
	
	/**
	 * Setter for the width of the sprite.
	 * @param width the new width of the sprite.
	 */
	public void setWidth(float width) {
		this.width = width;
	}
	
	/**
	 * Getter for the filename of the sprite.
	 * @return a String containing the filename of the sprite.
	 */
	public String getFilename() {
		return filename;
	}
}