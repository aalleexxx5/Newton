package common.data;



import common.services.Drawable;
import common.services.EntityPart;

import java.util.ArrayList;
import java.util.UUID;

public abstract class Entity implements Drawable {
    protected float[] location;
    UUID id;
    private Sprite sprite;
    
    private final ArrayList<EntityPart> entityParts = new ArrayList<>(6);

    public Entity() {
        id = UUID.randomUUID();
    }
    public Sprite draw() {
	    if (sprite == null) {
		    sprite = getSprite();
	    }
    	sprite.x = location[0];
    	sprite.y = location[1];
    	return sprite;
    }

	public abstract Sprite getSprite();
	
	protected void addEntityPart(EntityPart part){
    	entityParts.add(part);
	}
	
	public ArrayList<EntityPart> getEntityParts() {
		return entityParts;
	}

}
