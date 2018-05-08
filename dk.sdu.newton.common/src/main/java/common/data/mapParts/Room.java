package common.data.mapParts;

import common.data.Entity;

import java.util.ArrayList;

/**
 * The Room colllects an amount of the entities doors and walls to make standard room on the map.
 */
public class Room {


    private int number;
    private ArrayList<Entity> entities = new ArrayList<>();

    public Room() {

    }

    /**
     * The room it self and what Entities it contains. The parameters decides how many doors exists in the room.
     * @param North Refers to the upper part of the screen.
     * @param West Refers to the left part of the screen.
     * @param South Refers to the bottom part of the screen.
     * @param East Refers to the right part of the screen.
     * @param number A way to diffritiate the different rooms from eachother.
     */
    public Room(Boolean North, Boolean West, Boolean South, Boolean East, int number) {
        if (!North) {
            entities.add(new Wall("NDoorWall", 368, 688, 64, 64));

        } else {
            entities.add(new Door("northDoor", 368, 688, 64, 64));
        }

        if (!West) {
            entities.add(new Wall("WDoorWall", -32, 328, 64, 64));

        } else {
            entities.add(new Door("westDoor", -32, 328, 64, 64));

        }
        if (!South) {
            entities.add(new Wall("SDoorWall", 368, -32, 64, 64));

        } else {
            entities.add(new Door("southDoor", 368, -32, 64, 64));
        }
        if (!East) {
            entities.add(new Wall("EDoorWall", 768, 328, 64, 64));

        } else {
            entities.add(new Door("eastDoor", 768, 328, 64, 64));
        }
        createWalls();
        this.number = number;
    }

    public ArrayList<Entity> getEntities() {


        return entities;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }


    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public Door getNorthDoor() {
        if (entities.get(0) instanceof Door) {
            return (Door) entities.get(0);
        } else {
            return null;
        }
    }

    public Door getWestDoor() {
        if (entities.get(1) instanceof Door) {
            return (Door) entities.get(1);
        } else {
            return null;
        }
    }

    public Door getSouthDoor() {
        if (entities.get(2) instanceof Door) {
            return (Door) entities.get(2);
        } else {
            return null;
        }
    }

    public Door getEastDoor() {
        if (entities.get(3) instanceof Door) {
            return (Door) entities.get(3);
        } else {
            return null;
        }
    }

    /**
     * Creates the walls for the rooms
     */
    private void createWalls() {
        entities.add(new Wall("bigNWBox", -144, 688, 512, 32));
        entities.add(new Wall("bigNEBox", 432, 688, 512, 32));
        entities.add(new Wall("bigSWBox", -144, 0, 512, 32));
        entities.add(new Wall("bigSEBox", 432, 0, 512, 32));
        entities.add(new Wall("smallNWBox", 0, 392, 32, 512));
        entities.add(new Wall("smallNEBox", 768, 392, 32, 512));
        entities.add(new Wall("smallSWBox", 0, -144, 32, 512));
        entities.add(new Wall("smallSWBox", 768, -144, 32, 512));
    }

    /**
     *Checks wether or not the room contains doors.
     */
    public boolean checkForDoors() {
        if (getNorthDoor() != null || getSouthDoor() != null || getEastDoor() != null || getWestDoor() != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * gets the number of the room.
     */
    public int getInt() {
        return number;
    }
}
