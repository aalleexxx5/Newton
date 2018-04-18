package dk.sdu.newton.weapons.crescentStrike;

import common.data.GameState;
import common.data.Projectile;
import common.data.ProjectileDirection;
import common.data.Sprite;
import common.services.Collidable;

public class CrescentStrikeBullet extends Projectile {
    public CrescentStrikeBullet(ProjectileDirection direction, float speed) {
        super(direction, speed);
    }

    @Override
    public Sprite getSprite() {
        return null;
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
        return null;
    }

    @Override
    public void setDestruct() {

    }

    @Override
    public void update(GameState state) {

    }
}
