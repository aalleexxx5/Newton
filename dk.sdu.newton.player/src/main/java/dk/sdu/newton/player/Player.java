package dk.sdu.newton.player;

import common.data.*;
import common.data.entityParts.InventoryPart;
import common.data.entityParts.LifePart;
import common.data.entityParts.MovingPart;
import common.data.mapParts.Map;
import common.services.Collidable;
import common.services.Destructable;
import common.services.Equipable;

import java.util.ArrayList;
import java.util.function.Consumer;

import static common.data.Hostility.KILLS_PLAYER;
import static common.data.Hostility.NO_EFFECT;
import static common.data.Hostility.PASSIVE;

/**
 * The playable unit, controllable by the user.
 */
public class Player extends Unit {
	private static final float MOVEMENT_SPEED = 200.0f; // Don't know the unit. Might be Pixels pr second.
	private final LifePart lives;
	private final MovingPart movement;
	private boolean verticalMovementWasPressed = false;
	private boolean horizontalMovementWasPressed = false;
	private final InventoryPart inventory;
	private ProjectileDirection direction = ProjectileDirection.NORTH;
	
	
	public Player(int x, int y) {
		location[0] = x;
		location[1] = y;
		
		lives = new LifePart(3);
		addEntityPart(lives);
		
		movement = new MovingPart(0, 0);
		addEntityPart(movement);
		
		inventory = new InventoryPart();
		addEntityPart(inventory);
		inventory.addItem(new AppleWeapon(), this);
		
		inputActions();
	}
	
	/**
	 * Registers action names and callback function for controlling the player.
	 *
	 * @see InputActionMap
	 */
	private void inputActions() {
		GameState gameState = Registrator.getInstance().getState(AvailableStates.PLAY_STATE);
		gameState.getInputActionMap().registerAction("up", getReceiveActionCallback());
		gameState.getInputActionMap().registerAction("left", getReceiveActionCallback());
		gameState.getInputActionMap().registerAction("down", getReceiveActionCallback());
		gameState.getInputActionMap().registerAction("right", getReceiveActionCallback());
		gameState.getInputActionMap().registerAction("shoot", getReceiveActionCallback());
	}
	
	/**
	 * Very textbook sprite.
	 *
	 * @see Entity#getSprite()
	 */
	@Override
	public Sprite getSprite() {
		return new Sprite("player.png", location[0], location[1], 32, 32);
	}
	
	/**
	 * Used to clone unites.
	 * @param x the x location of the cloned unit.
	 * @param y the y location of the cloned unit.
	 * @return
	 */
	@Override
	public Unit addAtLocation(int x, int y) {
		return new Player(x, y);
	}
	
	/**
	 * Bullet hostility should damage enemies.
	 */
	@Override
	public Hostility getBulletHostility() {
		return Hostility.KILLS_ENEMY;
	}
	
	/**
	 * Allow player to pass through other units.
	 */
	@Override
	public Hostility getHostility() {
		return NO_EFFECT;
	}
	
	
	/**
	 * Collision logic.
	 * @param source the offending object.
	 */
	@Override
	public void collidesWith(Collidable source) {
		if (source.getHostility() == KILLS_PLAYER) {
			takeDamage(source);
		}
		if (source.getHostility() == PASSIVE) {
			movement.revertToLastFrame(this);
			movement.halt();
		}
		if (source.getHostility() == Hostility.MOVER) {
			changeRoom(source);
		}
	}
	
	/**
	 * Takes damage after collosion.
	 * @param source the offending collidable.
	 */
	private void takeDamage(Collidable source) {
		lives.decrement();
		if (source instanceof Destructable) {
			((Destructable) source).setDestruct();
		}
	}
	
	/**
	 * Moves the player into a new room.
	 * @param source the (presumably) door.
	 */
	private void changeRoom(Collidable source) {
		ArrayList<Entity> tempList = new ArrayList<>();
		Entity tempEntity = null;
		Map map = Registrator.getInstance().getState(AvailableStates.PLAY_STATE).getMap();
		
		if (source.equals(map.getCurrentRoom().getNorthDoor())) {
			for (Entity entity : map.getCurrentRoom().getEntities()) {
				if (entity instanceof Player) {
					tempList.add(entity);
					tempEntity = entity;
					
				}
			}
			map.getCurrentRoom().getEntities().remove(tempEntity);
			map.setCurrentRoom(map.getRooms().get(map.getCurrentRoom().getNorthDoor().getRoomNumber()));
			getLocation()[0] = 384;
			getLocation()[1] = 34;
			map.getCurrentRoom().addEntity(tempList.get(0));
		}
		
		if (source.equals(map.getCurrentRoom().getEastDoor())) {
			for (Entity entity : map.getCurrentRoom().getEntities()) {
				if (entity instanceof Player) {
					tempList.add(entity);
					tempEntity = entity;
				}
			}
			map.getCurrentRoom().getEntities().remove(tempEntity);
			map.setCurrentRoom(map.getRooms().get(map.getCurrentRoom().getEastDoor().getRoomNumber()));
			getLocation()[0] = 34;
			getLocation()[1] = 344;
			map.getCurrentRoom().addEntity(tempList.get(0));
		}
		
		if (source.equals(map.getCurrentRoom().getSouthDoor())) {
			for (Entity entity : map.getCurrentRoom().getEntities()) {
				if (entity instanceof Player) {
					tempList.add(entity);
					tempEntity = entity;
				}
			}
			map.getCurrentRoom().getEntities().remove(tempEntity);
			map.setCurrentRoom(map.getRooms().get(map.getCurrentRoom().getSouthDoor().getRoomNumber()));
			getLocation()[0] = 384;
			getLocation()[1] = 654;
			map.getCurrentRoom().addEntity(tempList.get(0));
		}
		
		if (source.equals(map.getCurrentRoom().getWestDoor())) {
			for (Entity entity : map.getCurrentRoom().getEntities()) {
				if (entity instanceof Player) {
					tempList.add(entity);
					tempEntity = entity;
				}
			}
			map.getCurrentRoom().getEntities().remove(tempEntity);
			map.setCurrentRoom(map.getRooms().get(map.getCurrentRoom().getWestDoor().getRoomNumber()));
			getLocation()[0] = 720;
			getLocation()[1] = 344;
			map.getCurrentRoom().addEntity(tempList.get(0));
		}
	}
	
	/**
	 * Default implementation.
	 *
	 * @see Entity#defaultBounds()
	 */
	@Override
	public float[] getBounds() {
		return defaultBounds();
	}
	
	/**
	 * Used to determine whether the player should be removed from the game area.
	 * Destructs upon death.
	 * @return {@code true}, if the player has no more lives.
	 */
	@Override
	public Boolean shouldDestruct() {
		return lives.isDead();
	}
	
	/**
	 * Other objects way of telling us that we have taken damage.
	 */
	@Override
	public void setDestruct() {
		lives.decrement();
	}
	
	/**
	 * Called each frame.
	 * Delegates event to smaller methods.
	 * @param state where the object is contained.
	 */
	@Override
	public void update(GameState state) {
		resetMovement();
		calculateSimpleDirection();
	}
	
	/**
	 * Used to calculate the direction to shoot in.
	 * This is done by returning the last direction of movement.
	 */
	private void calculateSimpleDirection() {
		// A more advanced version, could use the mouse location.
		int dirX = 0;
		int dirY = 0;
		if (movement.getDx() != 0) {
			dirX = movement.getDx() > 0 ? 1 : -1;
		}
		if (movement.getDy() != 0) {
			dirY = movement.getDy() > 0 ? 1 : -1;
		}
		if (dirX != 0 || dirY != 0) {
			direction = new ProjectileDirection(dirX, dirY);
		}
	}
	
	/**
	 * Used to stop the player, after a movement kay bas been released.
	 * Without this functionality, the player will only stop upon collision.
	 */
	private void resetMovement() {
		if (verticalMovementWasPressed) {
			verticalMovementWasPressed = false;
		} else {
			movement.setDy(0);
		}
		if (horizontalMovementWasPressed) {
			horizontalMovementWasPressed = false;
		} else {
			movement.setDx(0);
		}
	}
	
	/**
	 * Used as replacement for a lambda expression.
	 *
	 * @return a consumer which calls the {@link #receiveAction(String)} function.
	 */
	private Consumer<String> getReceiveActionCallback() {
		return new Consumer<String>() {
			@Override
			public void accept(String s) {
				receiveAction(s);
			}
		};
	}
	
	/**
	 * Called when an action has been recieved from a controller input.
	 * @param actionName the name of the action received.
	 */
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
	
	/**
	 * contains logic for moving the player on action.
	 * @param direction the direction of movement.
	 */
	private void receiveMovement(String direction) {
		switch (direction) {
			case "up":
			case "down":
				verticalMovementWasPressed = true;
				if (direction.equals("up")) movement.setDy(MOVEMENT_SPEED);
				else movement.setDy(-1 * MOVEMENT_SPEED);
				break;
			case "left":
			case "right":
				horizontalMovementWasPressed = true;
				if (direction.equals("right")) movement.setDx(MOVEMENT_SPEED);
				else movement.setDx(-1 * MOVEMENT_SPEED);
				break;
		}
	}
	
	/**
	 * contains the logic for firing, when called.
	 */
	private void receiveShoot() {
		inventory.shoot(Registrator.getInstance().getState(AvailableStates.PLAY_STATE), direction);
	}
	
	/**
	 * Convenience function.
	 * Adds an {@link Equipable} to the inventory.
	 * @param equipable the {@link Equipable} to add.
	 */
	void addEquipable(Equipable equipable) {
		inventory.addItem(equipable, this);
	}
}
