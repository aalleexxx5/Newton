package dk.sdu.newton.apprentice;



import common.data.GameState;
import common.data.Sprite;
import common.data.Unit;
import common.services.Collidable;

public class Apprentice extends Unit {
    int hp = 100;
    boolean shouldDestuct =false;
    private Sprite sprite;
    private float dx;
    private float dy;

    @Override
    public Sprite draw() {

        return null;
    }

    public Apprentice(String filename,float x, float y, float width, float height){
    sprite = new Sprite(filename,x,y,width,height);

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


}
