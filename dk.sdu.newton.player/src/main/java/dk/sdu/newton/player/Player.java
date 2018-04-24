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
import static common.data.Hostility.PASSIVE;

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
	public Unit addAtLocation(int x, int y) {
		return new Player(x,y);
	}
	
	@Override
	public Hostility getBulletHostility() {
		return Hostility.KILLS_ENEMY;
	}
	
	@Override
	public Hostility getHostility() {
		return KILLS_PLAYER;
	}
	
	@Override
	public void collidesWith(Collidable source) {
		if (source.getHostility() == KILLS_PLAYER) {
			lives.decrement();
			location[0] = 400;
			location[1] = 400;
			movement.setDx(0);
			movement.setDy(0);
			System.out.println("player lost a life!");
			if (source instanceof Destructable){
				((Destructable) source).setDestruct();
			}
		}if (source.getHostility() == PASSIVE){
			movement.revertToLastFrame(this);
			movement.setDx(0);
			movement.setDy(0);
		}
		if (source.getHostility() == Hostility.MOVER){
			ArrayList<Entity> tempList = new ArrayList<>();
			Entity tempEntity = null;
            Map map = Registrator.getInstance().getState(AvailableStates.PLAY_STATE).getMap();
            if (source.equals(map.getCurrentRoom().getNorthDoor())){
            	for (Entity entity : map.getCurrentRoom().getEntities()){
            		if (entity instanceof Player){
            			tempList.add(entity);
						tempEntity = entity;

					}
				}
				map.getCurrentRoom().getEntities().remove(tempEntity);
				map.setCurrentRoom(map.getRooms().get(map.getCurrentRoom().getNorthDoor().getRoomNumber() - 1));
            	getLocation()[0] = 384;
            	getLocation()[1] = 34;
				map.getCurrentRoom().addEntity(tempList.get(0));
			}

			if (source.equals(map.getCurrentRoom().getEastDoor())){
				for (Entity entity : map.getCurrentRoom().getEntities()){
					if (entity instanceof Player){
						tempList.add(entity);
						tempEntity = entity;
					}
				}
				map.getCurrentRoom().getEntities().remove(tempEntity);
				map.setCurrentRoom(map.getRooms().get(map.getCurrentRoom().getEastDoor().getRoomNumber() - 1));
				getLocation()[0] = 34;
				getLocation()[1] = 344;
				map.getCurrentRoom().addEntity(tempList.get(0));
			}

			if (source.equals(map.getCurrentRoom().getSouthDoor())){
				for (Entity entity : map.getCurrentRoom().getEntities()){
					if (entity instanceof Player){
						tempList.add(entity);
						tempEntity = entity;
					}
				}
				map.getCurrentRoom().getEntities().remove(tempEntity);
				map.setCurrentRoom(map.getRooms().get(map.getCurrentRoom().getSouthDoor().getRoomNumber() - 1));
				getLocation()[0] = 384;
				getLocation()[1] = 654;
				map.getCurrentRoom().addEntity(tempList.get(0));
			}

			if (source.equals(map.getCurrentRoom().getWestDoor())){
				for (Entity entity : map.getCurrentRoom().getEntities()){
					if (entity instanceof Player){
						tempList.add(entity);
						tempEntity = entity;
					}
				}
				map.getCurrentRoom().getEntities().remove(tempEntity);
				map.setCurrentRoom(map.getRooms().get(map.getCurrentRoom().getWestDoor().getRoomNumber() - 1));
				getLocation()[0] = 766;
				getLocation()[1] = 344;
				map.getCurrentRoom().addEntity(tempList.get(0));
			}

		}
	}
	
	@Override
	public float[] getBounds() {
		return new float[] {location[0], location[1], 32f,32f};
	}
	
	@Override
	public Boolean shouldDestruct() {
		return lives.getLives() <= 0;
	}
	
	@Override
	public void setDestruct() {
		lives.decrement(); // Other objects way of telling us that we have taken damage.
	}
	
	@Override
	public void update(GameState state) {
		resetMovement();
		calculateSimpleDirection();
	}
	
	private void calculateSimpleDirection() {
		int dirX = 0;
		int dirY = 0;
		if (movement.getDx() != 0){
			dirX = movement.getDx() > 0 ? 1 :-1;
		}
		if (movement.getDy() != 0){
			dirY = movement.getDy() > 0 ? 1:-1;
		}
		if (dirX != 0 ||dirY !=0){
			direction = new ProjectileDirection(dirX, dirY);
		}
	}
	
	private void resetMovement() {
		if (verticalMovementWasPressed){
			verticalMovementWasPressed = false;
		}else{
			movement.setDy(0);
		}
		if (horizontalMovementWasPressed){
			horizontalMovementWasPressed = false;
		}else{
			movement.setDx(0);
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
	
	private void receiveShoot() {
		inventory.shoot(Registrator.getInstance().getState(AvailableStates.PLAY_STATE), direction);
	}

	void addEquipable(Equipable equipable) {
		inventory.addItem(equipable, this);
	}
}
