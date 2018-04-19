package common.data;


import common.services.Collidable;
import common.services.Destructable;
import common.services.Updatable;

public abstract class Unit extends Entity implements Updatable, Collidable, Destructable {
	public abstract Unit addAtLocation(int x, int y);
	public abstract Hostility getBulletHostility();
}
