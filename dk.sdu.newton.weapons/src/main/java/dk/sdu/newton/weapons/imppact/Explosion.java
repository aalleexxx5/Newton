package dk.sdu.newton.weapons.imppact;

import common.data.Entity;
import common.data.GameState;
import common.data.Hostility;
import common.data.Sprite;
import common.services.Collidable;
import common.services.Destructable;
import common.services.Updatable;

public class Explosion extends Entity implements Collidable, Destructable, Updatable{
	private final Hostility hostility;
	private final float maxDuration = 0.8f;
	private float currentDuration = 0f;
	private final float WIDTH = 100;
	private final float HEIGHT = 100;
	private float currentWidth = 0;
	private float currentHeight = 0;
	private static final String FILENAME = "player.png";
	
	public Explosion(Hostility hostility, float[] location) {
		this.hostility = hostility;
		this.location = location;
	}
	
	@Override
	public Sprite draw() {
		return new Sprite(FILENAME, location[0], location[1], currentWidth, currentHeight);
	}
	
	@Override
	public Sprite getSprite() {
		return null;
	}
	
	@Override
	public Enum getHostility() {
		return hostility;
	}
	
	@Override
	public void collidesWith(Collidable source) {
	
	}
	
	@Override
	public float[] getBounds() {
		return new float[] {location[0], location[1], currentWidth, currentHeight};
	}
	
	@Override
	public Boolean shouldDestruct() {
		return currentDuration >= maxDuration;
	}
	
	@Override
	public void setDestruct() {
	
	}
	
	@Override
	public void update(GameState state) {
		currentDuration += state.getDeltaTime();
		if (currentDuration < maxDuration/2){
			currentWidth = WIDTH*(currentDuration/(maxDuration/2));
			currentHeight = HEIGHT*(currentDuration/(maxDuration/2));
		}else{
			currentWidth = WIDTH-WIDTH*(currentDuration-maxDuration/2)/(maxDuration/2);
			currentHeight = HEIGHT-HEIGHT*(currentDuration-maxDuration/2)/(maxDuration/2);
		}
	}
}
