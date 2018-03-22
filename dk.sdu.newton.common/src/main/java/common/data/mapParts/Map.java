package common.data.mapParts;

import java.util.ArrayList;

public class Map {
    Room currentRoom;
    ArrayList<Room> rooms = new ArrayList<>();

    public Room getCurrentRoom(){
        return currentRoom;
    }
}
