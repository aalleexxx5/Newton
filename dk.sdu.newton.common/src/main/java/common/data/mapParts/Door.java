package common.data.mapParts;

import common.data.Entity;
import common.data.Sprite;
import common.services.Collidable;

import static common.data.Hostility.MOVER;


public class Door extends Entity implements Collidable{

private int roomNumber;

    Sprite sprite;


    public Door (String name, float x, float y, float width, float height){
    sprite = new Sprite("door.png",0,0,32,32);
    }
    public int getRoomNumber(){
        return roomNumber;
    }

    public void setConnection(int roomNumber){
        this.roomNumber=roomNumber;
    }

	
	@Override
	public Sprite getSprite() {

        return sprite;
	}
	
	@Override
    public Enum getHostility() {
        return MOVER;
    }

    @Override
    public void collidesWith(Collidable source) { }

    @Override
    public float[] getBounds() {
        return new float[4];
    }
}
