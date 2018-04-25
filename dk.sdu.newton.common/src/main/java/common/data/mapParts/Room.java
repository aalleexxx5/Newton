package common.data.mapParts;

import common.data.Entity;

import java.util.ArrayList;

public class Room {


    private int number;
    private ArrayList<Entity> entities = new ArrayList<>();

    public Room() {

    }

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

    public boolean checkForDoors() {
        if (getNorthDoor() != null || getSouthDoor() != null || getEastDoor() != null || getWestDoor() != null) {
            return true;
        } else {
            return false;
        }
    }

    public int getInt() {
        return number;
    }
}
