package common.data.mapParts;

import common.data.Entity;
import common.data.Hostility;
import common.data.Sprite;
import common.services.Collidable;

import java.sql.Connection;

import static common.data.Hostility.KILLS_PLAYER;
import static common.data.Hostility.MOVER;
import static common.data.Hostility.PASSIVE;


public class Door extends Entity implements Collidable{

private int roomNumber;

    Sprite sprite;


    public Door (String name, float x, float y, float width, float height){
    sprite = new Sprite("door2.png",0,0,64,64);
        location[0]=x;
        location[1]=y;
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
    public Hostility getHostility() {
        return MOVER;
    }

    @Override
    public void collidesWith(Collidable source) { }

    @Override
    public float[] getBounds() {
        return new float[] {location[0],location[1], sprite.getWidth(),sprite.getHeight()};
    }
}
