package dk.sdu.newton.apprentice;
import common.data.GameState;
import common.data.Sprite;
import common.data.Unit;
import common.services.Collidable;

import static common.data.Hostility.KILLS_ENEMY;
import static common.data.Hostility.KILLS_PLAYER;
import static common.data.Hostility.PASSIVE;

public class Apprentice extends Unit {
    int hp = 100;
    boolean shouldDestuct =false;
    private Sprite sprite;
    private float dx;
    private float dy;

    @Override
    public Sprite draw() {

        return sprite;
    }

    public Apprentice(String filename,float x, float y, float width, float height){
    sprite = new Sprite(filename,x,y,width,height);

    }
    @Override
    public Enum getHostility() {

        return PASSIVE;
    }

    @Override
    public void collidesWith(Collidable source) {
        Enum i = source.getHostility();
        if (common.data.Hostility.PASSIVE.equals(i)) {
            //cant walk through
        } else if (common.data.Hostility.KILLS_PLAYER.equals(i)) {
            //tothing happens
        } else if (common.data.Hostility.KILLS_ENEMY.equals(i)) {
            hp = hp - 50;
        }
    }

    @Override
    public float[] getBounds() {
        float[] bounds = new float[4];
        bounds[0] = sprite.getX();
        bounds[1] = sprite.getY();
        bounds[2] = sprite.getWidth();
        bounds[3] = sprite.getHeight();
        return bounds;
    }


    @Override
    public Boolean shouldDestruct() {

        return shouldDestuct;
    }

    @Override
    public void setDestruct() {
        if (hp <= 0 ) {
            shouldDestuct=true;
        }
    }


    @Override
    public void update(GameState state) {
        sprite.setX(sprite.getX() + dx * state.getDeltaTime()) ;
        sprite.setY(sprite.getY() + dy * state.getDeltaTime()) ;


    }


}
