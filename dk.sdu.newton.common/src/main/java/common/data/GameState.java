package common.data;

import common.services.Updatable;

import java.util.ArrayList;

public class GameState {
	//Data:
	private ArrayList<Entity> gameEntities = new ArrayList<>(120);
	private ArrayList<Updatable> updatables = new ArrayList<>();
	private ArrayList<Updatable> postUpdateables = new ArrayList<>();
	private InputActionMap inputActionMap = new InputActionMap();
	
	// Map Todo
	
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
		return gameEntities;
	}
	
	public void addEntity(Entity entity){
		gameEntities.add(entity);
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
		for (Entity gameEntity : gameEntities) {
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
		gameEntities.remove(player);
	}
}
