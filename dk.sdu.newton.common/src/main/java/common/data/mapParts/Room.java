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
         entities.add(new Wall("NDoorWall",-50, 540, 100, 50));

     }
     else{
         entities.add(new Door("northDoor", -50, 540, 100, 50));
     }

     if(!West){
         entities.add(new Wall("WDoorWall",-600, 50, 50, 100));

     }
     else{
         entities.add(new Door("westDoor", -600, 50, 50, 100));

     }
     if(!South){
         entities.add(new Wall("SDoorWall", -50, -515, 100, 50));

     }
     else{
         entities.add(new Door("southDoor", -50, -515, 100, 50));
     }
     if(!East){
         entities.add(new Wall("EDoorWall", 575, 50, 50, 100));

     }
     else{
         entities.add(new Door("eastDoor", 575, 50, 50, 100 ));
     }
     createWalls();
    }

    public ArrayList<Entity> getEntities() {
        return entities;
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
        entities.add(new Wall("bigNWBox", -550, 540, 500, 50));
        entities.add(new Wall("bigNEBox", 50, 540, 500, 50));
        entities.add(new Wall("bigSWBox", -550, -490, 500, 50));
        entities.add(new Wall("bigSEBox", 50, -490, 500, 50));
        entities.add(new Wall("smallNWBox", -600, 540, 50, 490));
        entities.add(new Wall("smallNEBox", 550, 540, 50, 490));
        entities.add(new Wall("smallSWBox", -600, -50, 50, 490));
        entities.add(new Wall("smallSWBox", -600, -50, 50, 490));
    }
}