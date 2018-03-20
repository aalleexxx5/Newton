package dk.sdu.newton.apprentice;

import common.data.Entity;
import common.services.Collidable;
import common.services.Updatable;

public class Bullet extends Entity implements Collidable,Updatable{

    @Override
    public void draw() {

    }

    @Override
    public Enum getHostility() {
        return null;
    }

    @Override
    public void collidesWith() {

    }

    @Override
    public void update() {

    }
}
