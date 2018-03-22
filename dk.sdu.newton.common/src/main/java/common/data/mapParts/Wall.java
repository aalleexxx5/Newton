package common.data.mapParts;

import common.data.Entity;
import common.data.Sprite;
import common.services.Collidable;

import java.util.ArrayList;

class Wall extends Entity implements Collidable{
    float x, y, width, height;
    String name;
    private ArrayList<Entity> wallList = new ArrayList<>();

    public Wall (String name, float x, float y, float width, float height){
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    private void createBigNWBox() {
        Entity entity = new Wall("bigNWBox", -550, 540, 500, 50);
        wallList.add(entity);
    }

    private void createBigNEBox() {
        Entity entity = new Wall("bigNEBox", 50, 540, 500, 50);
        wallList.add(entity);
    }

    private void createBigSWBox() {
        Entity entity = new Wall("bigSWBox", -550, -490, 500, 50);
        wallList.add(entity);
    }

    private void createBigSEBox() {
        Entity entity = new Wall("bigSEBox", 50, -490, 500, 50);
        wallList.add(entity);
    }

    private void createSmallNWBox() {
        Entity entity = new Wall("smallNWBox", -600, 540, 50, 490);
        wallList.add(entity);
    }

    private void createSmallNEBox() {
        Entity entity = new Wall("smallNEBox", 550, 540, 50, 490);
        wallList.add(entity);
    }

    private void createSmallSWBox() {
        Entity entity = new Wall("smallSWBox", -600, -50, 50, 490);
        wallList.add(entity);
    }

    private void createSmallSEBox() {
        Entity entity = new Wall("smallSEBox", 550, -50, 50, 490);
        wallList.add(entity);
    }

    

    ArrayList<Entity> getWallList(){
        createBigNEBox();
        createBigNWBox();
        createBigSEBox();
        createBigSWBox();
        createSmallNEBox();
        createSmallNWBox();
        createSmallSEBox();
        createSmallSWBox();
        return wallList;
    }


    @Override
    public Sprite draw() {

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
}
