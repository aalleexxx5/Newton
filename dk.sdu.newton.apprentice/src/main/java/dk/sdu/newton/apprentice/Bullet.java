package dk.sdu.newton.apprentice;

import common.data.Entity;
import common.data.GameState;
import common.data.Sprite;
import common.services.Collidable;
import common.services.Updatable;

public class Bullet extends Entity implements Collidable,Updatable{

    @Override
    public Sprite draw() {

    }

    @Override
    public Enum getHostility() {
        return null;
    }

    @Override
    public void collidesWith() {

    }

    @Override
    public void update(GameState state) {

    }
}
