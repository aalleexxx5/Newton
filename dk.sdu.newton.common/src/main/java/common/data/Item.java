package common.data;
import common.services.Collidable;

public abstract class Item extends Entity implements Collidable {
    public abstract void collidesWith();
    
}
