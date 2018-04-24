package dk.sdu.newton.apprentice;

import common.data.Entity;
import common.data.GameState;
import common.data.Projectile;
import common.data.Unit;

import java.util.ArrayList;

import static common.data.Hostility.KILLS_ENEMY;

public class ApprenticeControl {
    private Apprentice source;
    private float movement[] = new float[2];
    private float dx,dy,dodgespeed, newdx, newdy,x,y;
    private float sensorRange = 300;
    private float speed = 25;
    private float wallSensorRange = 30;
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Unit> units = new ArrayList<>();
    private ArrayList<Entity> roomEntities = new ArrayList<>();

    public ApprenticeControl(Apprentice source){
        this.source = source;
    }

    public void makeEviorment(GameState state) {
        units.clear();
        setPosition();
        roomEntities = state.getMap().getCurrentRoom().getEntities();
        entities  = state.getGameEntities();
        for (int i =0 ; i <entities.size();i++) {
            if (entities.get(i) instanceof Unit) {
                units.add((Unit) entities.get(i));
            }


        }}
    public void wallSensors() {
        newdx = dx;
        newdy = dy;

//checks if einstein is close to walls
        if (y + wallSensorRange > roomEntities.get(0).getLocation()[1]){
            newdy=-speed*3;
            newdx=speed;
            //System.out.println("N");
        }
        if (x - wallSensorRange < 32){
            newdx=speed*3;
            newdy=speed;
            //System.out.println("W");
        }
        if (y -  wallSensorRange < 32){
            newdy=speed*3;
            newdx=-speed;
            //System.out.println("S");
        }
        if (x > roomEntities.get(3).getLocation()[0]){
            newdx=-speed*3;
            newdy=-speed;
            //System.out.println("E");
        }
        dx = newdx;
        dy = newdy;
    }

    private void setPosition(){
        x = source.getBounds()[0]+(source.getBounds()[2]/2);
        y = source.getBounds()[1]+(source.getBounds()[3]/2);
    }

    public void sensors(){

    for(int i =0 ; i <units.size();i++){
        if (x + sensorRange < units.get(i).getBounds()[0]
                || x - sensorRange > units.get(i).getBounds()[0]
                || y + sensorRange < units.get(i).getBounds()[1]
                || y - sensorRange > units.get(i).getBounds()[1]
                ) {
            newdx=0;
            newdy=0;

        }else{

            if (x < units.get(i).getBounds()[0]) {
                newdx = speed;
            } else if (x>= units.get(i).getBounds()[0]) {
                newdx = -speed;
            }
            if (y < units.get(i).getBounds()[1]) {
                newdy = speed;
            } else if (y>= units.get(i).getBounds()[1]) {
                newdy = -speed;
            }

    }
    }
    dx=newdx;
    dy=newdy;
    }


    public float getdx(){
      return dx;
    }
        public float getdy(){
            return dy;

}}
