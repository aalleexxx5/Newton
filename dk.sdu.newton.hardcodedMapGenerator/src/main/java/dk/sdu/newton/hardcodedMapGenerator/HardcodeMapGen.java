package dk.sdu.newton.hardcodedMapGenerator;

import common.data.AvailableStates;
import common.data.Entity;
import common.data.Registrator;
import common.data.Sprite;
import common.data.mapParts.Door;
import common.data.mapParts.Map;
import common.data.mapParts.Room;
import common.data.mapParts.Wall;

import java.util.ArrayList;



public class HardcodeMapGen {
    ArrayList<Room> rooms = new ArrayList<>();
    Map map;
    public HardcodeMapGen() {
        createMap();
    }



    private void createMap(){

        map = new Map();
        Room room1 = new Room(true, false, false, false, 1);
        Room room2 = new Room(true, false, true, false, 2);
        Room room3 = new Room(false, false, true, false, 3);


        room1.getNorthDoor().setConnection(2);

        room2.getSouthDoor().setConnection(1);
        room2.getNorthDoor().setConnection(3);

        room3.getSouthDoor().setConnection(2);

        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

        room2.addEntity(Registrator.getInstance().getEnemy(200).addAtLocation(400,400));
        room2.addEntity(Registrator.getInstance().getEnemy(200).addAtLocation(200,400));
        room2.addEntity(Registrator.getInstance().getEnemy(200).addAtLocation(100,400));
        room3.addEntity(Registrator.getInstance().getEnemy(1000).addAtLocation(360,400));



        map.addRoomsToMap(room1);
        map.addRoomsToMap(room2);
        map.addRoomsToMap(room3);

        map.setCurrentRoom(room1);
    }

    public Map getMap(){
        return map;
    }



    }

