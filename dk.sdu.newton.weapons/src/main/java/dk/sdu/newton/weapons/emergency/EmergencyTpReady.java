package dk.sdu.newton.weapons.emergency;

import common.data.Entity;
import common.data.Sprite;
import common.services.Destructable;
import common.services.Drawable;
import org.osgi.service.http.NamespaceException;

/**
 * A visual effect following the wielder of a ready teleport.
 * Used to indicate to the user, that a unit is ready to teleport.
 */
public class EmergencyTpReady extends Entity implements Destructable {
    private static final String FILENAME = "blueThinSpiral.png";
    private boolean destruct = false;
    private static final float WIDTH = 64;
    private static final float HEIGHT = 64;

    /**
     * Does not clone the received location array.
     * This makes the entity automatically follow the parent.
     *
     * @param location the location of the parent to be followed.
     */
    public EmergencyTpReady(float[] location) {
        this.location = location;
    }

    /**
     * Overridden, as the location needs to be modified, to center the sprite.
     *
     * @return the sprite to draw on screen.
     * @see Drawable#draw()
     */
    @Override
    public Sprite draw() {
        return new Sprite(FILENAME, location[0] - (WIDTH - 32) / 2, location[1] - (HEIGHT - 32) / 2, WIDTH, HEIGHT);
    }

    /**
     * Not needed, as the base {@link Drawable#draw()} method is overridden.
     *
     * @return null, as the method will not be used.
     */
    @Override
    public Sprite getSprite() {
        return null;
    }

    /**
     * Textbook destruct behavior.
     *
     * @see Destructable#shouldDestruct()
     */
    @Override
    public Boolean shouldDestruct() {
        return destruct;
    }

    /**
     * Textbook destruct behavior.
     *
     * @see Destructable#setDestruct()
     */
    @Override
    public void setDestruct() {
        destruct = true;
    }
}
