package dk.sdu.newton.player;

import common.data.*;
import common.data.entityParts.LifePart;
import common.data.entityParts.MovingPart;
import common.services.Collidable;

import java.util.logging.Logger;

public class Player extends Unit {
	private static final float MOVEMENT_SPEED = 50.0f; // Don't know the unit. Might be Pixels pr second.
	private final LifePart lives;
	private final MovingPart movement;
	private final Logger logger = Logger.getLogger(getClass().getName());
	
	private float oldX,oldY;
	
	public Player() {
		lives = new LifePart(3);
		addEntityPart(lives);
		
		movement = new MovingPart(0,0);
		addEntityPart(movement);
		
		GameState gameState = Registrator.getInstance().getState(AvailableStates.PLAY_STATE);
		gameState.getInputActionMap().registerAction("up", this::receiveAction);
		gameState.getInputActionMap().registerAction("left", this::receiveAction);
		gameState.getInputActionMap().registerAction("down", this::receiveAction);
		gameState.getInputActionMap().registerAction("right", this::receiveAction);
		gameState.getInputActionMap().registerAction("shoot", this::receiveAction);
	}
	
	@Override
	public Sprite getSprite() {
		return new Sprite("player.png", location[0], location[1], 32,32);
	}
	
	@Override
	public Enum getHostility() {
		return null;
	}
	
	@Override
	public void collidesWith(Collidable source) {
		if (source.getHostility() == Hostility.KILLS_PLAYER) {
			lives.decrement();
		}
	}
	
	@Override
	public float[] getBounds() {
		return new float[0];
	}
	
	@Override
	public Boolean shouldDestruct() {
		return lives.getLives() >= 0;
	}
	
	@Override
	public void setDestruct() {
		lives.decrement(); // Other objects way of telling us that we have taken damage.
	}
	
	@Override
	public void update(GameState state) {
		float newX = location[0];
		float newY = location[1];
		if (oldX != newX){
			logger.info("Location changed! New x: "+newX);
			oldX = newX;
		}
		if (oldY != newY){
			logger.info("Location changed! New y: "+newY);
			oldY = newY;
		}
	}
	
	private void receiveAction(String actionName) {
		switch (actionName) {
			case "up":
			case "left":
			case "down":
			case "right":
				receiveMovement(actionName);
				break;
			case "shoot":
				receiveShoot();
				break;
		}
	}
	
	private void receiveMovement(String direction) {
		switch (direction){
			case "up":
			case "down":
				movement.setDy((direction.equals("up") ? -1 : 1) * MOVEMENT_SPEED);
				break;
			case "left":
			case "right":
				movement.setDy((direction.equals("right") ? -1 : 1) * MOVEMENT_SPEED);
				break;
		}
	}
	
	private void receiveShoot() {
		// TODO:
	}
}
