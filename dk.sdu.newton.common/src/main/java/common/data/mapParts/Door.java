package common.data.mapParts;

import common.data.Entity;
import common.data.Sprite;
import common.services.Collidable;


public class Door extends Entity implements Collidable{

private int roomNumber;

    Sprite sprite;


    public Door (String name, float x, float y, float width, float height){
    sprite = new Sprite("door.png",x,y,width,height);
    }
    public int getRoomNumber(){
        return roomNumber;
    }

    public void setConnection(int roomNumber){
        this.roomNumber=roomNumber;
    }

    @Override
    public Sprite draw() {
        return sprite;
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
    public void collidesWith(Collidable source) { }

    @Override
    public float[] getBounds() {
        return new float[0];
    }
}
