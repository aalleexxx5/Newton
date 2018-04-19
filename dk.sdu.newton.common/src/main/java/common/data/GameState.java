package common.data;

import common.data.mapParts.Door;
import common.data.mapParts.Map;
import common.data.mapParts.Wall;
import common.services.Updatable;

import java.util.ArrayList;

public class GameState {
	//Data:
	private ArrayList<Updatable> postUpdateables = new ArrayList<>();
	private ArrayList<Entity> spawnList = new ArrayList<>();
	private ArrayList<Entity> tempRemoveList = new ArrayList<>();
	private InputActionMap inputActionMap = new InputActionMap();
	private Map map = new Map();


	// Map Todo
	public void setMap(Map map){
		this.map = map;
	}

	public Map getMap(){
		return map;
	}
	
	public GameState() {
	}
	
	private int width, height;
	
	private float deltaTime;
	public float getDeltaTime() {
		return deltaTime;
	}
	
	public void setDeltaTime(float deltaTime) {
		this.deltaTime = deltaTime;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}

	public void addToSpawnList(){
		for (Entity entity : map.getCurrentRoom().getEntities()) {
			if (entity instanceof Wall) {
				tempRemoveList.add(entity);
			}
			if (entity instanceof Door) {
				tempRemoveList.add(entity);			}
		}
		map.getCurrentRoom().getEntities().removeAll(tempRemoveList);
		spawnList.addAll(map.getCurrentRoom().getEntities());
	}

	public ArrayList<Entity> getSpawnList() {
		return spawnList;
	}

	public void removeSpawnListEntities(){
		spawnList.clear();
	}

	public ArrayList<Entity> getGameEntities(){

		return map.getCurrentRoomEntityList();
	}
	
	public void addEntity(Entity entity){
		map.addEntityToCurrentRoom(entity);
	}
	
	public void addPostUpdatable(Updatable postUpdatable){
		postUpdateables.add(postUpdatable);
	}
	
	public void removePostUpdatable(Updatable postUpdatable){
		postUpdateables.remove(postUpdatable);
	}
	
	public InputActionMap getInputActionMap() {
		return inputActionMap;
	}
	
	/**
	 * Get all entities implementing a specific interface
	 * @param implementedInterface
	 * @param <T>
	 * @return
	 */
	public <T> ArrayList<T> getEntitiesByInterface(Class<T> implementedInterface){
		// TODO test me!
		ArrayList<T> list = new ArrayList<T>();
		for (Entity gameEntity : map.getCurrentRoomEntityList()) {
			if (implementedInterface.isAssignableFrom(gameEntity.getClass())){
				list.add(implementedInterface.cast(gameEntity));
			}
		}
		return list;
	}
	
	public ArrayList<Updatable> getPostUpdateables() {
		return postUpdateables;
	}
	
	public void removeEntity(Entity player) {
		map.getCurrentRoom().getEntities().remove(player);
	}
}
