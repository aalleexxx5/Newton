package common.data.mapParts;

import common.data.AvailableStates;
import common.data.Entity;

import java.util.ArrayList;
import common.data.Registrator;

/**
 *The map handles all the different entities that exists in the rooms. Not only doors and walls.
 */

public class Map {

private ArrayList<Room> rooms = new ArrayList<>();
private static ArrayList<Entity> entityToAddList = new ArrayList<>();
private ArrayList<Entity> emptyList = new ArrayList<>();
private ArrayList<Entity> tempList = new ArrayList<>();

    /**
     * The currentRoom is the room which the player is currently residing in.
     */
    private Room currentRoom;
    public Room getCurrentRoom(){
        return currentRoom;
    }

    public ArrayList<Entity> getCurrentRoomEntityList(){
        if (currentRoom == null){
            return emptyList;
        }
        else {
            return currentRoom.getEntities();
        }
    }

    public void addRoomsToMap(Room room){
        rooms.add(room);
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * sets the currentRoom. if the currentRoom dosnt exists, a new Room is made
     */
    public void setCurrentRoom(Room room) {

        if (currentRoom == null) {
            this.currentRoom = room;
            for (Entity e : entityToAddList) {
                currentRoom.addEntity(e);
            }
        } else{
            this.currentRoom = room;
    }
    }


    public void addEntityToCurrentRoom(Entity entity){
        if (currentRoom == null){
            entityToAddList.add(entity);
        }
        else {
            currentRoom.addEntity(entity);
        }

    }

    /**
     * Sets the currentRoom when the module first loads in and loads all the different entities.
     */
    public void setCurrentRoomOnStart(Room room){
        tempList.addAll(entityToAddList);
        tempList.addAll(Registrator.getInstance().getState(AvailableStates.PLAY_STATE).getSpawnList());
        setCurrentRoom(room);
        for (Entity entity : tempList){
            currentRoom.addEntity(entity);
        }
        tempList.clear();
        Registrator.getInstance().getState(AvailableStates.PLAY_STATE).getSpawnList().clear();

    }


}
