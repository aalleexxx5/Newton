package common.data.mapParts;

import common.data.Entity;
import common.data.Sprite;
import common.services.Collidable;


public class Wall extends Entity implements Collidable{


    private Sprite sprite;




    public Wall (String name, float x, float y, float width, float height){
    sprite = new Sprite("Wall.png", 0, 0, width, height );
    location[0]=x;
    location[1]=y;
    }



	
	@Override
	public Sprite getSprite() {
		return sprite;
	}
	
	@Override
    public Enum getHostility() {
        return null;
    }

    @Override
    public void collidesWith(Collidable source) { }

    @Override
    public float[] getBounds() {
        return new float[4];
    }
}