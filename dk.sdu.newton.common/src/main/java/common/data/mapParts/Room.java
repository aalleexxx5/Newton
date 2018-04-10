package common.data.mapParts;

import common.data.Entity;

import java.util.ArrayList;

public class Room {
    private String name;
    private Wall wall;
    private Door door;
    private ArrayList<Entity> entities = new ArrayList<>();



    public Room(Boolean North, Boolean West, Boolean South, Boolean East, int number){


     if(!North){
         entities.add(new Wall("NDoorWall",368, 688, 64, 64));

     }
     else{
         entities.add(new Door("northDoor", 368, 688, 64, 64));
     }

     if(!West){
         entities.add(new Wall("WDoorWall",-32, 328, 64, 64));

     }
     else{
         entities.add(new Door("westDoor", -32, 328, 64, 64));

     }
     if(!South){
         entities.add(new Wall("SDoorWall", 368, -32, 64, 64));

     }
     else{
         entities.add(new Door("southDoor", 368, -32, 64, 64));
     }
     if(!East){
         entities.add(new Wall("EDoorWall", 768, 328, 64, 64));

     }
     else{
         entities.add(new Door("eastDoor", 575, 50, 64, 64 ));
     }
     createWalls();
    }

    public ArrayList<Entity> getEntities() {


        return entities;
    }

    public void addEntity(Entity entity){
        entities.add(entity);
    }

    public void removeEntity(Entity entity){
        entities.remove(entity);
    }

    public Door getNorthDoor(){
        if (entities.get(0) instanceof Door){
            return (Door) entities.get(0);
      }
      else{
            return null;
        }
  }
  public Door getWesthDoor(){
        if (entities.get(1) instanceof Door){
            return (Door) entities.get(1);
      }
      else{
            return null;
        }
  }
  public Door getSouthDoor(){
        if (entities.get(2) instanceof Door){
            return (Door) entities.get(2);
      }
      else{
            return null;
        }
  }
  public Door getEastDoor(){
        if (entities.get(3) instanceof Door){
            return (Door) entities.get(3);
      }
      else{
            return null;
        }
  }

    private void createWalls() {
        entities.add(new Wall("bigNWBox", 0, 688, 256, 32));
        entities.add(new Wall("bigNEBox", 544, 688, 256, 32));
        entities.add(new Wall("bigSWBox", 0, 0, 256, 32));
        entities.add(new Wall("bigSEBox", 544, 0, 256, 32));
        entities.add(new Wall("smallNWBox", 0, 464, 32, 256));
        entities.add(new Wall("smallNEBox", 768, 464, 32, 256));
        entities.add(new Wall("smallSWBox", 0, 0, 32, 256));
        entities.add(new Wall("smallSWBox", 768, 0, 32, 256));
    }
}
