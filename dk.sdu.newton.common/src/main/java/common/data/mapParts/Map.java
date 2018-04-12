package common.data.mapParts;

import common.data.Entity;

import java.util.ArrayList;

public class Map {

private ArrayList<Room> rooms = new ArrayList<>();
private static final ArrayList<Entity> entityToAddList = new ArrayList<>();
private ArrayList<Entity> emptyList = new ArrayList<>();

    private Room currentRoom = new Room(true, true, true, true, 1);
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

    public void setCurrentRoom(Room nextRoom) {

        if (currentRoom == null) {
            this.currentRoom = nextRoom;
            for (Entity e : entityToAddList) {
                currentRoom.addEntity(e);
            }
            entityToAddList.clear();
        } else{
            this.currentRoom = nextRoom;
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


}
