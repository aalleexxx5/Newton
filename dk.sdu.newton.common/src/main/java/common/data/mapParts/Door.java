package common.data.mapParts;

import common.data.Entity;
import common.data.Sprite;
import common.services.Collidable;

import java.util.ArrayList;

class Door extends Entity implements Collidable{
    float x, y, width, height;
    String name;
    Connections connection;
    private ArrayList<Entity> doorList = new ArrayList<>();

    public Door (String name, float x, float y, float width, float height){
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    private void createNorthDoor(){
        Entity entity = new Door("northDoor", -50, 540, 100, 50);
        doorList.add(entity);
    }

    private void createSouthDoor(){
        Entity entity = new Door("southDoor", -50, -515, 100, 50);
        doorList.add(entity);
    }

    private void createWestDoor(){
        Entity entity = new Door("westDoor", -600, 50, 50, 100);
        doorList.add(entity);
    }

    private void createEastDoor(){
        Entity entity = new Door("eastDoor", 575, 50, 50, 100);
        doorList.add(entity);
    }

    ArrayList<Entity> getDoorList(){
        createEastDoor();
        createNorthDoor();
        createSouthDoor();
        createWestDoor();
        return doorList;
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
