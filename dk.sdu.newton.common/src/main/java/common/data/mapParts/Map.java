package common.data.mapParts;

import common.data.AvailableStates;
import common.data.Entity;

import java.util.ArrayList;
import common.data.Registrator;

public class Map {

private ArrayList<Room> rooms = new ArrayList<>();
private static ArrayList<Entity> entityToAddList = new ArrayList<>();
private ArrayList<Entity> emptyList = new ArrayList<>();
private ArrayList<Entity> tempList = new ArrayList<>();


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

    public void setCurrentRoomOnStart(Room room){
        tempList.addAll(entityToAddList);
        tempList.addAll(Registrator.getInstance().getState(AvailableStates.PLAY_STATE).getSpawnList());
        setCurrentRoom(room);
        for (Entity entity : tempList){
            currentRoom.addEntity(entity);
        }
        tempList.clear();
    }

    public void setCurrentRoomToEmptyRoom(){
        tempList.addAll(entityToAddList);
        tempList.addAll(Registrator.getInstance().getState(AvailableStates.PLAY_STATE).getSpawnList());

        currentRoom = new Room();
        for (Entity entity : tempList){
            currentRoom.addEntity(entity);
        }
        tempList.clear();
    }
}
