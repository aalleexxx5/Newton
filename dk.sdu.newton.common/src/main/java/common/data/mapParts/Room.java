package common.data.mapParts;

import common.data.Entity;
import common.data.Sprite;

import java.util.ArrayList;

public class Room {
    private String name;
    private Wall wall;
    private Door door;
    private ArrayList<Entity> entities = new ArrayList<>();



    public Room(Boolean North, Boolean West, Boolean South, Boolean East, int number){


     if(North== false){
         entities.add(createNDoorWall());

     }
     else{
         entities.add(createNorthDoor());
     }

     if(West== false){
         entities.add(createWDoorWall());

     }
     else{
         entities.add(createWestDoor());

     }
     if(South== false){
         entities.add(createSDoorWall());

     }
     else{
         entities.add(createSouthDoor());
     }
     if(East== false){
         entities.add(createEDoorWall());

     }
     else{
         entities.add(createEastDoor());
     }
     addWalls();
    }


  private void addWalls(){
        entities.add(createBigNEBox());
        entities.add(createBigNWBox());
        entities.add(createBigSEBox());
        entities.add(createBigSWBox());
        entities.add(createSmallNEBox());
        entities.add(createSmallNWBox());
        entities.add(createSmallSWBox());
        entities.add(createSmallSEBox());
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
  public Door getSouthhDoor(){
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

    private Wall createBigNWBox() {
        Wall wall = new Wall("bigNWBox", -550, 540, 500, 50);

        return wall;
    }

    private Wall createBigNEBox() {
        Wall wall = new Wall("bigNEBox", 50, 540, 500, 50);

        return wall;
    }

    private Wall createBigSWBox() {
        Wall wall = new Wall("bigSWBox", -550, -490, 500, 50);

        return wall;
    }

    private Wall createBigSEBox() {
        Wall wall = new Wall("bigSEBox", 50, -490, 500, 50);

        return wall;
    }


    private Wall createSmallNWBox() {
        Wall wall = new Wall("smallNWBox", -600, 540, 50, 490);

        return wall;
    }

    private Wall createSmallNEBox() {
        Wall wall = new Wall("smallNEBox", 550, 540, 50, 490);

        return wall;
    }

    private Wall createSmallSWBox() {
        Wall wall = new Wall("smallSWBox", -600, -50, 50, 490);

        return wall;
    }

    private Wall createSmallSEBox() {
        Wall wall = new Wall("smallSEBox", 550, -50, 50, 490);

        return wall;
    }
    private Door createNorthDoor(){
        Door door = new Door("northDoor", -50, 540, 100, 50);
        return door;
    }

    private Door createSouthDoor(){
        Door door = new Door("southDoor", -50, -515, 100, 50);
        return door;
    }

    private Door createWestDoor(){
        Door door = new Door("westDoor", -600, 50, 50, 100);
        return door;
    }

    private Door createEastDoor() {
        Door door = new Door("eastDoor", 575, 50, 50, 100 );
        return door;

    }
    private Wall createNDoorWall(){
        Wall wall = new Wall("NDoorWall",-50, 540, 100, 50);
        return wall;
    }
    private Wall createWDoorWall(){
        Wall wall = new Wall("WDoorWall",-600, 50, 50, 100);
        return wall;
    }
    private Wall createSDoorWall() {
        Wall wall = new Wall("SDoorWall", -50, -515, 100, 50);
        return wall;
    }
    private Wall createEDoorWall() {
        Wall wall = new Wall("EDoorWall", 575, 50, 50, 100);
        return wall;
    }
}
