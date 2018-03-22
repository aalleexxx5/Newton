package common.data.mapParts;

import common.data.Entity;
import common.data.Sprite;

import java.util.ArrayList;

public class Room {
    private String name;
    private Wall walls;
    private Door doors;
    private ArrayList<Entity> entities = new ArrayList<>();

    public Room (String name){
        this.name = name;
    }

    public ArrayList<Entity> getEntities(){
        entities.addAll(walls.getWallList());
        entities.addAll(doors.getDoorList());
        return entities;
    }
}
