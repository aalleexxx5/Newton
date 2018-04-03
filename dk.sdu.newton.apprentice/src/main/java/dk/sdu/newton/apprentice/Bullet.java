package dk.sdu.newton.apprentice;

import common.data.Entity;
import common.data.GameState;
import common.data.Projectile;
import common.data.Sprite;
import common.services.Collidable;
import common.services.Updatable;

import static common.data.Hostility.KILLS_PLAYER;

public class Bullet extends Projectile {
    private boolean shouldDestruct = false;
    private Sprite sprite;
    private float dx;
    private float dy;
    private float speed = 10;

    public Bullet(String filename, float x, float y, float width, float height){
        sprite = new Sprite(filename, x , y, width, height);
    }
    
    @Override
    public Sprite getSprite() {
        return sprite;
    }
    
    @Override
    public Sprite getSprite() {
        return null;
    }

    @Override
    public Enum getHostility() {
        return KILLS_PLAYER;
    }

    @Override
    public void collidesWith(Collidable source) {
        Enum i = source.getHostility();
        if (common.data.Hostility.PASSIVE.equals(i)) {
            setDestruct();
        } else if (common.data.Hostility.KILLS_PLAYER.equals(i)) {
            setDestruct();
        } else if (common.data.Hostility.KILLS_ENEMY.equals(i)) {
            //contenue
        }
    }

    @Override
    public float[] getBounds() {
        float[] bounds = new float[3];
        bounds[0] = sprite.getX();
        bounds[1] = sprite.getY();
        bounds[2] = sprite.getWidth();
        bounds[3] = sprite.getHeight();
        return new float[0];
    }

    @Override
    public void update(GameState state) {
        sprite.setX(sprite.getX() + dx * state.getDeltaTime()*speed) ;
        sprite.setY(sprite.getY() + dy * state.getDeltaTime()*speed) ;
    }

    @Override
    public Boolean shouldDestruct() {
        return shouldDestruct;
    }

    @Override
    public void setDestruct() {
        shouldDestruct = true;
    }
}
