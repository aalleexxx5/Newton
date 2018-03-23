package dk.sdu.newton.player.internal;

import common.data.GameState;
import common.data.Hostility;
import common.data.Sprite;
import common.data.Unit;
import common.data.entityParts.LifePart;
import common.services.Collidable;

public class Player extends Unit {

    private Sprite sprite;
    private LifePart lp;
    private boolean shouldDestuct = false;
    private int hp = 100;

    @Override
    public Sprite draw() {
        return sprite;
    }

    /**
     * Used by other collidables to determine the hostility of this Collidable.
     *
     * @return the hostility of the collidable.
     */
    @Override
    public Enum getHostility() {
        return Hostility.NO_EFFECT;
    }

    /**
     * Called when a collision has been detected.
     *
     * @param source the offending object.
     */
    @Override
    public void collidesWith(Collidable source) {
        if(source.getHostility() == Hostility.KILLS_PLAYER){
            hp = hp - 25;
        }
        if(source.getHostility() == Hostility.PASSIVE){

        }

    }

    /**
     * Used by collision to get the bounding box for collision checking.
     *
     * @return an array containing the x,y,width,height of the Collidable.
     */
    @Override
    public float[] getBounds() {
        float[] playerBounds = new float[4];
        playerBounds[0] = sprite.getX();
        playerBounds[1] = sprite.getY();
        playerBounds[2] = sprite.getWidth();
        playerBounds[3] = sprite.getHeight();

        return playerBounds;
    }

    @Override
    public Boolean shouldDestruct() {
        return shouldDestuct;
    }

    @Override
    public void setDestruct() {
        if(lp.getLives() <= 0){
            shouldDestuct = true;
        }
    }

    @Override
    public void update(GameState state) {

    }
}
