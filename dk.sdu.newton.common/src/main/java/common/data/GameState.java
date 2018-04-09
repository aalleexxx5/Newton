package common.data;

import common.data.mapParts.Map;
import common.services.Updatable;

import java.util.ArrayList;

public class GameState {
	//Data:

	private ArrayList<Updatable> updatables = new ArrayList<>();
	private ArrayList<Updatable> postUpdateables = new ArrayList<>();
	private InputActionMap inputActionMap = new InputActionMap();
	private Map map = new Map();

	// Map Todo
	public void setMap(Map map){
		this.map = map;
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
	
	public ArrayList<Entity> getGameEntities(){

		return map.getCurrentRoom().getEntities();
	}
	
	public void addEntity(Entity entity){
		map.getCurrentRoom().addEntity(entity);
	}
	
	public void addUpdatable(Updatable updatable){
		updatables.add(updatable);
	}
	
	public void removeUpdatable(Updatable updatable){
		updatables.remove(updatable);
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
		for (Entity gameEntity : map.getCurrentRoom().getEntities()) {
			if (implementedInterface.isAssignableFrom(gameEntity.getClass())){
				list.add(implementedInterface.cast(gameEntity));
			}
		}
		return list;
	}
	
	public ArrayList<Updatable> getUpdatables() {
		return updatables;
	}
	
	public ArrayList<Updatable> getPostUpdateables() {
		return postUpdateables;
	}
	
	public void removeEntity(Entity player) {
		map.getCurrentRoom().getEntities().remove(player);
	}
}
