package dk.sdu.newton.weapons.Rake;

import common.data.*;

import java.util.Random;

public class RakeWeapon extends Weapon{
    Unit container;
    @Override
    public Item getDroppedItem() {
        return new RakeItem(container.getLocation());
    }

    @Override
    public void onShoot(GameState state, ProjectileDirection direction) {
//        for(int i=0;i<3;i++){
//        float rand = new Random().nextFloat();
//        state.addEntity(new RakeBullet(rotateDirection(direction, 1400*rand - 700), container));
//    }

        state.addEntity(new RakeBullet(direction,container));
        state.addEntity(new RakeBullet(rotateDirection(direction,100),container));
        state.addEntity(new RakeBullet(rotateDirection(direction,-100),container));
    }

    @Override
    public int getCooldownInMs() {
        return 500;
    }

    @Override
    public void onEquip(Unit container) {
        this.container = container;
    }



    private ProjectileDirection rotateDirection(ProjectileDirection direction,float rotation){
        float dirX = 0;
        float dirY = 0;
        float distance=0;
        dirX = getRotatedX(direction.getVectorXComponent(),direction.getVectorYComponent(),rotation);
        dirY = getRotatedY(direction.getVectorXComponent(),direction.getVectorYComponent(),rotation);
        distance = (float) Math.sqrt(Math.pow(dirX,2)+Math.pow(dirY,2));

        dirX = dirX*(1/distance);
        dirY = dirY*(1/distance);
        return new ProjectileDirection(dirX,dirY);
    }

    private float getRotatedX( float x, float y,float rotation){
        return (float) (x*Math.cos(rotation) - y*Math.sin(rotation));
    }
    private float getRotatedY(float x, float y,float rotation){
        return (float) (y*Math.cos(rotation) + x*Math.sin(rotation));
    }
}
