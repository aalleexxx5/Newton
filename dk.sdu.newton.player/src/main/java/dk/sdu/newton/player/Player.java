package dk.sdu.newton.player;

import common.data.*;
import common.data.entityParts.LifePart;
import common.data.entityParts.MovingPart;
import common.services.Collidable;

import java.util.function.Consumer;
import java.util.logging.Logger;

public class Player extends Unit {
	private static final float MOVEMENT_SPEED = 50.0f; // Don't know the unit. Might be Pixels pr second.
	private final LifePart lives;
	private final MovingPart movement;
	private final Logger logger = Logger.getLogger(getClass().getName());
	private float oldX, oldY;
	
	public Player() {
		lives = new LifePart(3);
		addEntityPart(lives);
		
		movement = new MovingPart(0, 0);
		addEntityPart(movement);
		
		GameState gameState = Registrator.getInstance().getState(AvailableStates.PLAY_STATE);
		gameState.getInputActionMap().registerAction("up", getReceiveActionCallback());
		gameState.getInputActionMap().registerAction("left", getReceiveActionCallback());
		gameState.getInputActionMap().registerAction("down", getReceiveActionCallback());
		gameState.getInputActionMap().registerAction("right", getReceiveActionCallback());
		gameState.getInputActionMap().registerAction("shoot", getReceiveActionCallback());
	}
	
	@Override
	public Sprite getSprite() {
		return new Sprite("player.png", location[0], location[1], 32, 32);
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
		location = new float[2];
		float newX = location[0] = 200;
		float newY = location[1] = 200;
		if (oldX != newX) {
			logger.info("Location changed! New x: " + newX);
			oldX = newX;
		}
		if (oldY != newY) {
			logger.info("Location changed! New y: " + newY);
			oldY = newY;
		}
	}
	
	private Consumer<String> getReceiveActionCallback() {
		return new Consumer<String>() {
			@Override
			public void accept(String s) {
				receiveAction(s);
			}
		};
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
		System.out.println("Movement recieved: "+direction);
		switch (direction) {
			case "up":
			case "down":
				if (direction.equals("up")) movement.setDy(-1 * MOVEMENT_SPEED);
				else movement.setDy(1 * MOVEMENT_SPEED);
				break;
			case "left":
			case "right":
				if (direction.equals("right")) movement.setDy(-1 * MOVEMENT_SPEED);
				else movement.setDy(1 * MOVEMENT_SPEED);
				break;
		}
	}
	
	private void receiveShoot() {
		// TODO:
	}
}
