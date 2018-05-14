package common.data;

import common.data.mapParts.Door;
import common.data.mapParts.Map;
import common.data.mapParts.Wall;
import common.services.Updatable;

import java.util.ArrayList;

/**
 * Class containing all data of the gameState.
 * This includes all entities on screen along with the map and updatables.
 */
public class GameState {
	//Data:
	
	public GameState() {
		Map.clearEntitiesToSpawn();
	}
	
	private ArrayList<Updatable> postUpdatables = new ArrayList<>();
	private ArrayList<Entity> spawnList = new ArrayList<>();
	private ArrayList<Entity> tempRemoveList = new ArrayList<>();
	private InputActionMap inputActionMap = new InputActionMap();
	private Map map = new Map();
	private int width, height;
	private float deltaTime;
	
	
	/**
	 * Adds all entities excluding room parts to the spawn list.
	 */
	public void addToSpawnList() {
		for (Entity entity : map.getCurrentRoom().getEntities()) {
			if (entity instanceof Wall) {
				tempRemoveList.add(entity);
			}
			if (entity instanceof Door) {
				tempRemoveList.add(entity);
			}
		}
		map.getCurrentRoom().getEntities().removeAll(tempRemoveList);
		spawnList.addAll(map.getCurrentRoom().getEntities());
	}
	
	/**
	 * Used to get a list of entities to add in when a new map is set.
	 * @return an {@link ArrayList<Entity>} containing all entities to initially insert.
	 */
	public ArrayList<Entity> getSpawnList() {
		return spawnList;
	}
	
	/**
	 * Add an {@link Entity} to the current map.
	 * @param entity the entity to add to the current map.
	 */
	public void addEntity(Entity entity) {
		map.addEntityToCurrentRoom(entity);
	}
	
	/**
	 * Adds a PostUpdatable to the game.
	 * @param postUpdatable the {@link Updatable} to add.
	 */
	public void addPostUpdatable(Updatable postUpdatable) {
		postUpdatables.add(postUpdatable);
	}
	
	/**
	 * Used to remove a {@link Updatable} from associated postUpdatables of the gameState.
	 * @param postUpdatable the {@link Updatable} to remove.
	 */
	public void removePostUpdatable(Updatable postUpdatable) {
		postUpdatables.remove(postUpdatable);
	}
	
	/**
	 * Used to get an {@link InputActionMap} linked with this gameState.
	 * @return an {@link InputActionMap} contained in the gameState.
	 */
	public InputActionMap getInputActionMap() {
		return inputActionMap;
	}
	
	/**
	 * Used to obtain all gameEntries.
	 * @return an {@link ArrayList<Entity>} of all entities currently on screen.
	 */
	public ArrayList<Entity> getGameEntities() {
		return getEntitiesByInterface(Entity.class); // A very inefficient line of code!
	}
	
	/**
	 * Get all entities implementing a specific interface or abstract class.
	 *
	 * @param implementedInterface a reachable interface.
	 * @param <T>                  the type of the implementedInterface.
	 * @return an {@link ArrayList} containing all entities extending or implementing the given interface.
	 */
	public <T> ArrayList<T> getEntitiesByInterface(Class<T> implementedInterface) {
		ArrayList<T> list = new ArrayList<>();
		for (Entity gameEntity : map.getCurrentRoomEntityList()) {
			// If the implementedInterface type can be philosophically assigned to the gameEntity:
			if (implementedInterface.isAssignableFrom(gameEntity.getClass())) {
				list.add(implementedInterface.cast(gameEntity));
			}
		}
		return list;
	}
	
	/**
	 * Used to remove an entity from the game.
	 * @param entity the entity to remove.
	 */
	public void removeEntity(Entity entity) {
		map.getCurrentRoom().getEntities().remove(entity);
	}
	
	/**
	 * Used to get all postUpdatables.
	 * @return a list of updatables to be run after the rendering and updating of the regular updatables.
	 */
	public ArrayList<Updatable> getPostUpdatables() {
		return postUpdatables;
	}
	
	/**
	 * Setter for map contained within the gameState.
	 */
	public void setMap(Map map) {
		this.map = map;
	}
	
	/**
	 * Getter for the current map.
	 */
	public Map getMap() {
		return map;
	}
	
	/**
	 * Getter for the current deltaTime
	 * @return a float denoting time since last frame in seconds.
	 */
	public float getDeltaTime() {
		return deltaTime;
	}
	
	/**
	 * Setter for the current deltaTime
	 * @param deltaTime time in seconds since last frame.
	 */
	public void setDeltaTime(float deltaTime) {
		this.deltaTime = deltaTime;
	}
	
	/**
	 * Used to obtain the width of the play area.
	 * @return the width of the play area in pixels.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Used to set the width of the play area.
	 * @param width the width of the play area in pixels.
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Used to get the height of the play area.
 	 * @return the height of the play area in pixels.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Used to set the height of the play area.
	 * @param height the height of the play area in pixels.
	 */
	public void setHeight(int height) {
		this.height = height;
	}
}
