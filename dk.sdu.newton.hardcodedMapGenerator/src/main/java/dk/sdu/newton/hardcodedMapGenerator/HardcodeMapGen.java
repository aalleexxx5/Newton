package dk.sdu.newton.hardcodedMapGenerator;


import common.data.Registrator;
import common.data.mapParts.Door;
import common.data.mapParts.Map;
import common.data.mapParts.Room;

import java.util.ArrayList;
import java.util.Arrays;


public class HardcodeMapGen {
    ArrayList<Room> rooms = new ArrayList<>();
    Map map;

    public HardcodeMapGen() {
        createMap();
        // randomMap();
    }

    private void createMap() {

        map = new Map();
        Room room1 = new Room(true, false, false, false, 0);
        Room room2 = new Room(true, false, true, false, 1);
        Room room3 = new Room(false, false, true, false, 2);


        room1.getNorthDoor().setConnection(1);

        room2.getSouthDoor().setConnection(0);
        room2.getNorthDoor().setConnection(2);

        room3.getSouthDoor().setConnection(1);

        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

        room2.addEntity(Registrator.getInstance().getEnemy(200).addAtLocation(400, 400));
        room2.addEntity(Registrator.getInstance().getEnemy(200).addAtLocation(200, 400));
        room2.addEntity(Registrator.getInstance().getEnemy(200).addAtLocation(100, 400));
        room3.addEntity(Registrator.getInstance().getEnemy(1000).addAtLocation(360, 400));


        map.addRoomsToMap(room1);
        map.addRoomsToMap(room2);
        map.addRoomsToMap(room3);

        map.setCurrentRoom(room1);
    }

    public Map getMap() {
        return map;
    }


    public void randomMap() throws ArrayIndexOutOfBoundsException {
        map = new Map();
        boolean[][] mapMatrix = new boolean[5][5];
        Room[][] roomMatrix = new Room[5][5];
        boolean north, west, south, east;

        for (int row = 0; row < mapMatrix.length; row++) {
            for (int col = 0; col < mapMatrix[row].length; col++) {
                mapMatrix[row][col] = Math.random() < 0.5;
            }
        }
        for (int row = 0; row < mapMatrix.length; row++) {
            for (int col = 0; col < mapMatrix[row].length; col++) {
                if (mapMatrix[row][col] == true) {
                    try {
                        //check north
                        if (mapMatrix[row - 1][col] == true) {
                            north = true;
                        } else {
                            north = false;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        north = false;
                    }
                    try {
                        //check west
                        if (mapMatrix[row][col - 1] == true) {
                            west = true;
                        } else {
                            west = false;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        west = false;
                    }
                    try {
                        //check south
                        if (mapMatrix[row + 1][col] == true) {
                            south = true;
                        } else {
                            south = false;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        south = false;
                    }
                    try {
                        //check east
                        if (mapMatrix[row][col + 1] == true) {
                            east = true;
                        } else {
                            east = false;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        east = false;
                    }
                    Room room = new Room(north, west, south, east, row * 5 + col);
                    roomMatrix[row][col] = room;

                } else {
                    Room room = new Room(false, false, false, false, row * 5 + col);
                    roomMatrix[row][col] = room;
                }
            }
        }

        for (int row = 0; row < roomMatrix.length; row++) {
            for (int col = 0; col < roomMatrix[row].length; col++) {
                try {
                    //set north connection
                    if (roomMatrix[row - 1][col].getSouthDoor() instanceof Door && roomMatrix[row][col].getNorthDoor() instanceof Door) {
                        roomMatrix[row][col].getNorthDoor().setConnection(roomMatrix[row - 1][col].getInt());
                        roomMatrix[row - 1][col].getSouthDoor().setConnection(roomMatrix[row][col].getInt());
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Connection could not be made");
                }
                try {
                    //set west connection
                    if (roomMatrix[row][col - 1].getEastDoor() instanceof Door && roomMatrix[row][col].getWestDoor() instanceof Door) {
                        roomMatrix[row][col].getWestDoor().setConnection(roomMatrix[row][col - 1].getInt());
                        roomMatrix[row][col - 1].getEastDoor().setConnection(roomMatrix[row][col].getInt());
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Connection could not be made");
                }
                try {
                    //set south connection
                    if (roomMatrix[row + 1][col].getNorthDoor() instanceof Door && roomMatrix[row][col].getSouthDoor() instanceof Door) {
                        roomMatrix[row][col].getSouthDoor().setConnection(roomMatrix[row + 1][col].getInt());
                        roomMatrix[row + 1][col].getNorthDoor().setConnection(roomMatrix[row][col].getInt());
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Connection could not be made");
                }
                try {
                    //set east connection
                    if (roomMatrix[row][col + 1].getWestDoor() instanceof Door && roomMatrix[row][col].getEastDoor() instanceof Door) {
                        roomMatrix[row][col].getEastDoor().setConnection(roomMatrix[row][col + 1].getInt());
                        roomMatrix[row][col + 1].getWestDoor().setConnection(roomMatrix[row][col].getInt());
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Connection could not be made");
                }
            }
        }

        for (int row = 0; row < roomMatrix.length; row++) {
            for (int col = 0; col < roomMatrix[row].length; col++) {
                map.addRoomsToMap(roomMatrix[row][col]);
            }
        }

        for (int row = 0; row < roomMatrix.length; row++) {
            for (int col = 0; col < roomMatrix[row].length; col++) {
                if (roomMatrix[row][col].checkForDoors() != false) {
                    rooms.add(roomMatrix[row][col]);
                }
            }
        }

        int random = (int) Math.random() * rooms.size() + 1;

        map.setCurrentRoom(rooms.get(random));
        System.out.println("Starting in room: " + rooms.get(random).getInt());
    }

}

