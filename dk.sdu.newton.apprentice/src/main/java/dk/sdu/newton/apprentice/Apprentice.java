package dk.sdu.newton.apprentice;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import common.data.GameState;
import common.data.Unit;
import common.services.Collidable;

public class Apprentice extends Unit {
    int hp = 100;
    boolean shouldDestuct =false;
    private Sprite sprite;
    private Texture texture;
    private float dx;
    private float dy;

    @Override
    public void draw() {

    }

    public Apprentice(){
    this.sprite = new Sprite();

    //widhth and height / random Todo
    location[0]=100;
    location[1]=100;

    }
    @Override
    public Enum getHostility() {
        return null;
    }

    @Override
    public void collidesWith(Collidable source) {

    }

    @Override
    public float[] getBounds() {
        return new float[0];
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
        location[0] += dx * state.getDeltaTime();
        location[1] += dy * state.getDeltaTime();


    }

    public Sprite getSprite(){
        return sprite;
    }

    public void create() {
        texture = new Texture(Gdx.files.internal("Assets/Apprentice.png"));
        sprite = new Sprite(texture);
    }
}
