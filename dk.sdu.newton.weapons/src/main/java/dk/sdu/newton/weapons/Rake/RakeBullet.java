package dk.sdu.newton.weapons.Rake;

import common.data.*;
import common.data.entityParts.MovingPart;
import common.services.Collidable;
import common.services.EntityPart;

import java.lang.reflect.Array;
import java.sql.Time;
import java.util.HashSet;
import java.util.Random;

public class RakeBullet extends Projectile {
    private static final float SPEED = 700f;
    private static final float OUTER_SPEED = SPEED*0.1f;
    private static final String FILENAME = "rakebullet.png";
    private static final float WIDTH = 16;
    private static final float HEIGHT = 16;
    private  final int DURATION = 2*((TIME * 2) + AIR_TIME);
    private static final int TIME = 50;
    private static final int AIR_TIME = 1000;
    private MovingPart mover = null;
    private boolean destruct = false;
    private float rotationRate = 45;
    private float rotationRate2 = -45;
    private long startTime = System.currentTimeMillis();
    private float newDX;
    private float newDY;

    public RakeBullet(ProjectileDirection direction, Unit origin) {
        super(direction, SPEED, origin);
       float x=direction.getVectorXComponent();
       float y=direction.getVectorYComponent();




        this.location = origin.getLocation().clone();
        for (EntityPart entityPart : getEntityParts()) {
            if (entityPart instanceof MovingPart) {
                mover = (MovingPart) entityPart;
            }
        }
        newDX = (float) (mover.getDx()*0.1);
        newDY = (float) (mover.getDy()*0.1);
    }

    @Override
    public Sprite getSprite() {
        return new Sprite(FILENAME, 0, 0, WIDTH, HEIGHT);
    }


    @Override
    public void collidesWith(Collidable source) {
        if (source == origin) return;
        if (source instanceof Projectile) return;
        if (source instanceof Item) return;

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
        boolean bool = false;
        long currentTime = System.currentTimeMillis();

        if (currentTime - startTime >= TIME*3) {

            mover.setDx(newDX);
            mover.setDy(newDY);

        }
        float rand = new Random().nextFloat();

        if (currentTime - startTime >= TIME*5+(rand*100)) {



            if (location[0] < origin.getBounds()[0]+origin.getBounds()[2]/2) {
                mover.setDx(SPEED);
            } else if (location[0]>= origin.getBounds()[0]+origin.getBounds()[2]/2) {
                mover.setDx(-SPEED);
            }
            if (location[1] < origin.getBounds()[1]+origin.getBounds()[3]/2) {
                mover.setDy(SPEED);
            } else if (location[1]>= origin.getBounds()[1]+origin.getBounds()[3]/2) {
                mover.setDy(-SPEED);
            }



    }
        if (currentTime - startTime >= TIME*7+(rand*100)) {
            setDestruct();
    }


}}
