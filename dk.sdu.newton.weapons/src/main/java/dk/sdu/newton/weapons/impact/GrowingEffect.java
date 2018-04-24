package dk.sdu.newton.weapons.impact;

import common.data.*;
import common.services.Destructable;
import common.services.Updatable;

public class GrowingEffect extends Entity implements Destructable, Updatable {
	private final float maxDuration;
	private final float WIDTH;
	private final float HEIGHT;
	private final String FILENAME;
	private final float[] originLocation;
	private float currentDuration = 0f;
	private float currentWidth = 0;
	private float currentHeight = 0;
	
	public GrowingEffect(float[] location, String filename, float width, float height, float durationSeconds) {
		this.originLocation = location.clone();
		this.FILENAME = filename;
		this.WIDTH = width;
		this.HEIGHT = height;
		this.maxDuration = durationSeconds;
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
	public void update(GameState state) {
		currentDuration += state.getDeltaTime();
		if (currentDuration < maxDuration / 2) {
			currentWidth = WIDTH * (currentDuration / (maxDuration / 2));
			currentHeight = HEIGHT * (currentDuration / (maxDuration / 2));
		} else {
			currentWidth = WIDTH - WIDTH * (currentDuration - maxDuration / 2) / (maxDuration / 2);
			currentHeight = HEIGHT - HEIGHT * (currentDuration - maxDuration / 2) / (maxDuration / 2);
		}
		location[0] = originLocation[0] - currentWidth / 2;
		location[1] = originLocation[1] - currentHeight / 2;
	}
	
	@Override
	public Boolean shouldDestruct() {
		return currentDuration >= maxDuration;
	}
	
	@Override
	public void setDestruct() {
	
	}
}