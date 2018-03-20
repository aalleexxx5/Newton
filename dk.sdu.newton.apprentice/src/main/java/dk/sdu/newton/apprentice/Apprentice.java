package dk.sdu.newton.apprentice;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import common.data.Unit;

public class Apprentice extends Unit {
    int hp = 100;
    boolean shouldDestuct =false;
    private Sprite sprite;
    private Texture texture;
    @Override
    public void draw() {

    }

    @Override
    public Enum getHostility() {
        return null;
    }

    @Override
    public void collidesWith() {
        //cant walk through apprentice

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
    public void update() {

    }
}
