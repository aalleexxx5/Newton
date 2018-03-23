package dk.sdu.newton.hardcodedMapGenerator;

import common.data.mapParts.Door;
import common.data.mapParts.Room;
import common.data.mapParts.Wall;

import java.util.ArrayList;



public class HardcodeMapGen {
    ArrayList<Room> rooms = new ArrayList<>();
    public HardcodeMapGen() {
        createMap();
    }



    private void createMap(){


        Room room1 = new Room(true, false, false, false, 1);
        Room room2 = new Room(true, false, true, false, 2);
        Room room3 = new Room(false, false, true, false, 3);

        room1.getNorthDoor().setConnection(2);

        room2.getSouthhDoor().setConnection(1);
        room2.getNorthDoor().setConnection(3);

        room3.getSouthhDoor().setConnection(2);

        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

    }



    }

