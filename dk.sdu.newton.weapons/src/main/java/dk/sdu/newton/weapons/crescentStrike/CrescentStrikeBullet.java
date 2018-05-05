package dk.sdu.newton.weapons.crescentStrike;

import common.data.*;
import common.data.entityParts.MovingPart;
import common.services.Collidable;
import common.services.EntityPart;
import common.services.Updatable;

import java.util.HashSet;

public class CrescentStrikeBullet extends Projectile implements Updatable {
    private static final float SPEED = 400f;
    private static final String FILENAME = "crescentmoon.png";
    private static final float WIDTH = 16;
    private static final float HEIGHT = 16;
    private static final int DURATION = 950;
    private final Unit parent;
    private boolean destruct = false;
    private MovingPart mover;
    private long startTime = System.currentTimeMillis();
    private float rotationRate = (float) Math.PI;
    private final HashSet<Collidable> damaged = new HashSet<>();


    public CrescentStrikeBullet(ProjectileDirection direction, Unit unit) {
        super(ProjectileDirection.clockwiseRotation(direction), SPEED, unit);
        location = unit.getLocation().clone();
        parent = unit;
        mover = getEntityPart(MovingPart.class);
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
        if (source == origin) return;
        if (source instanceof Projectile) return;
        if (source instanceof Item) return;
        if (damaged.contains(source)) return;
        damaged.add(source);
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

        if (currentTime - startTime >= DURATION) {
            setDestruct();
        }
    }
}
