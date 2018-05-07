package dk.sdu.newton.weapons.crescentStrike;

import common.data.*;
import common.data.entityParts.MovingPart;
import common.services.Collidable;
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


    /**
     * Constructor for bullet to make more bullets
     *
     * @param direction the direction of the player
     * @param unit      to get info about the unit player
     */
    public CrescentStrikeBullet(ProjectileDirection direction, Unit unit) {
        super(ProjectileDirection.clockwiseRotation(direction), SPEED, unit);
        location = unit.getLocation().clone();
        parent = unit;
        mover = getEntityPart(MovingPart.class);
    }

    /**
     * returns the the sprite of the png file
     *
     * @return the sprite of the png file
     */
    @Override
    public Sprite getSprite() {
        return new Sprite(FILENAME, 0, 0, WIDTH, HEIGHT);
    }

    /**
     * hostility of the bullet to figure out what should happen at collision
     *
     * @return the hostility of the bullet
     */
    @Override
    public Hostility getHostility() {
        return parent.getBulletHostility();
    }

    /**
     * Specifies what objects the bullet is allowed to
     * collide with and how to interact with them
     *
     * @param source the offending object.
     */
    @Override
    public void collidesWith(Collidable source) {
        if (source == origin) return;
        if (source instanceof Projectile) return;
        if (source instanceof Item) return;
        if (damaged.contains(source)) return;
        damaged.add(source);
    }

    /**
     * Gets the location, width and height
     *
     * @return
     */
    @Override
    public float[] getBounds() {
        return new float[]{location[0], location[1], WIDTH, HEIGHT};
    }

    /**
     * Should the bullet destruct
     *
     * @return destroy bullet false/true
     */
    @Override
    public Boolean shouldDestruct() {
        return destruct;
    }

    /**
     * Destructs the bullet
     */
    @Override
    public void setDestruct() {
        destruct = true;
    }

    /**
     * Projectile trajectory calculation for CrescentStrikeBullet
     *
     * @param state where the object is contained.
     */
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
