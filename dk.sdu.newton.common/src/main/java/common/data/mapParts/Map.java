package common.data.mapParts;

import java.util.ArrayList;

public class Map {

private ArrayList<Room> rooms = new ArrayList<>();

    private Room currentRoom;
    public Room getCurrentRoom(){
        return currentRoom;
    }

    public void addRoomsToMap(Room room){
        rooms.add(room);
    }

    public void setCurrentRoom(Room nextRoom){
        this.currentRoom = nextRoom;
    }


}
