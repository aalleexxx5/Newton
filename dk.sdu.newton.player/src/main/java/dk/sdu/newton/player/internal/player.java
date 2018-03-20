package dk.sdu.newton.player.internal;

import common.data.GameState;
import common.data.Unit;
import common.services.Collidable;

public class player extends Unit {

    @Override
    public void draw() {

    }

    /**
     * Used by other collidables to determine the hostility of this Collidable.
     *
     * @return the hostility of the collidable.
     */
    @Override
    public Enum getHostility() {
        return null;
    }

    /**
     * Called when a collision has been detected.
     *
     * @param source the offending object.
     */
    @Override
    public void collidesWith(Collidable source) {

    }

    /**
     * Used by collision to get the bounding box for collision checking.
     *
     * @return an array containing the x,y,width,height of the Collidable.
     */
    @Override
    public float[] getBounds() {
        return new float[0];
    }

    @Override
    public Boolean shouldDestruct() {
        return null;
    }

    @Override
    public void setDestruct() {

    }

    @Override
    public void update(GameState state) {

    }
}
