package common.services;

import common.data.Sprite;

/**
 * Any implementing object can be rendered to the screen.
 */
public interface Drawable {
	
	/**
	 * Called every frame to obtain a {@link Sprite} to be drawn.
	 * @return a sprite with a filename, location and size
	 * of an image to render on screen.
	 */
    Sprite draw();
}
