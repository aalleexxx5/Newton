package dk.sdu.newton.weapons.crescentStrike;

import common.data.*;
import common.data.entityParts.MovingPart;
import common.services.Collidable;
import common.services.EntityPart;

public class CrescentStrikeBullet extends Projectile {
    private static final float SPEED = 400f;
    private static final String FILENAME = "player.png";
    private static final float WIDTH = 16;
    private static final float HEIGHT = 16;
    private static final int DURATION = 950;
    private final Unit parent;
    private boolean destruct = false;
    private MovingPart mover = null;
    private long startTime = System.currentTimeMillis();
    private float rotationRate = (float) Math.PI;


    public CrescentStrikeBullet(ProjectileDirection direction, Unit unit) {
        super(ProjectileDirection.clockwiseRotation(direction), SPEED, unit);
        location = unit.getLocation().clone();
        parent = unit;
        for (EntityPart entityPart : getEntityParts()) {
            if (entityPart instanceof MovingPart) {
                mover = (MovingPart) entityPart;
            }
        }
    }

    @Override
    public Sprite getSprite() {
        return new Sprite(FILENAME, 0, 0, WIDTH, HEIGHT);
    }

    @Override
    public Hostility getHostility() {
        return parent.getBulletHostility();
    }

    @Override
    public void collidesWith(Collidable source) {
        if (source == parent) return;
        setDestruct();
    }

    @Override
    public float[] getBounds() {
        return new float[]{location[0], location[1], WIDTH, HEIGHT};
    }

    @Override
    public Boolean shouldDestruct() {
        return destruct;
    }

    @Override
    public void setDestruct() {
        destruct = true;
    }

    @Override
    public void update(GameState state) {
        float x = mover.getDx();
        float y = mover.getDy();
        float dx = -y;
        float dy = +x;
        long currentTime = System.currentTimeMillis();

        dx = dx * (rotationRate * state.getDeltaTime());
        dy = dy * (rotationRate * state.getDeltaTime());

        mover.setDx(x + dx);
        mover.setDy(y + dy);


        /*
        float curve1 = 1.75f;
        float curve2 = 1.00f;

	    if (currentTime - startTime <= DURATION/4) {
            mover.setDy(x + curve1);
            mover.setDy(y + curve1);

        } else if (currentTime - startTime <= DURATION/2) {
	        mover.setDx(x + curve2);
	        mover.setDy(y + curve2);

        } else if (currentTime - startTime >= DURATION/2) {
            mover.setDx(x - curve2);
            mover.setDy(y - curve2);

        } else if (currentTime - startTime <= DURATION/4 * 3) {
            mover.setDx(x - curve1);
            mover.setDy(y - curve1);
        }
*/
        if (currentTime - startTime >= DURATION) {
            setDestruct();
        }
    }
}
