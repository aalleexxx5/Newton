package dk.sdu.newton.einstein;

import common.data.*;
import common.data.entityParts.MovingPart;
import common.services.Collidable;

import java.util.ArrayList;
import java.util.Random;

import static common.data.Hostility.KILLS_ENEMY;

public class EinsteinControl {

	private Einstein einstein;
	private float movement[] = new float[2];
	private Random random = new Random(5260);
	private ArrayList<Entity> entities = new ArrayList<>();
	private ArrayList<Unit> units = new ArrayList<>();
	private ArrayList<Unit> players = new ArrayList<>();
	private ArrayList<Projectile> projectiles = new ArrayList<>();
	private ArrayList<Collidable> collideables = new ArrayList<>();
	private ArrayList<float[]> boundaries = new ArrayList<>();
	private ArrayList<float[]> einsteinsHitbox;
	private float sensorRange=100;
	private float wallSensorRange=50;
	private float aimSensorRange=500;
	private float dx,dy,speed,dodgespeed, newdx, newdy;
    private ArrayList<Entity> roomEntities = new ArrayList<>();


    public EinsteinControl(Einstein einstein){
		this.einstein = einstein;
		dx=0;
		dy=0;
		speed = 75;
		dodgespeed=100;
	}

	public void makeEviorment(GameState state) {
		boundaries.clear();
		units.clear();
		collideables.clear();
		projectiles.clear();
        roomEntities = state.getMap().getCurrentRoom().getEntities();
		entities  = state.getGameEntities();
		for (int i =0 ; i <entities.size();i++) {
			if (entities.get(i) instanceof Unit) {
				units.add((Unit) entities.get(i));
			}
            if (entities.get(i) instanceof Projectile && ((Projectile) entities.get(i)).getHostility()==KILLS_ENEMY) {
                projectiles.add((Projectile) entities.get(i));
            }


		}


      //  System.out.println(mapParts.size());


		for (int i = 0; i < units.size()-1; i++) {
			float[] temp = new float[4];
			temp[0] = units.get(i).getBounds()[0];
			temp[1] = units.get(i).getBounds()[1] +(units.get(i)).getBounds()[3];
			temp[2] = units.get(i).getBounds()[0] + (units.get(i)).getBounds()[2];
			temp[3] = units.get(i).getBounds()[1];
			boundaries.add(temp);
		}


        einsteinsHitbox = createHitbox(einstein.getBounds()[0],
                einstein.getBounds()[1] + einstein.getBounds()[3],
                einstein.getBounds()[0] + einstein.getBounds()[2],
                einstein.getBounds()[1]);
	}
	//x=bonds[0]
	//y=bonds[1]+H
	//g=bonds[0]+W
	//h=bonds[1]
	private ArrayList<float[]> createHitbox(float x, float y, float g, float h){
		ArrayList<float[]> hitbox = new ArrayList<>();
		float[] a = new float[2],b= new float[2],c= new float[2],d= new float[2],e = new float[2];
		//Cordinates for each point on the squares
		// x                y
		a[0]=x;         a[1]=y+h;
		b[0]=x+g;       b[1]=y+h;
		c[0]=x;         c[1]=y;
		d[0]=x+g;       d[1]=y;
		e[0]=x+(g-x)/2; e[1]=y+(h-y)/2;
		hitbox.add(a);
		hitbox.add(b);
		hitbox.add(c);
		hitbox.add(d);
		hitbox.add(e);
		return hitbox;
	}
	public void playerSensors() {
newdx=dx;
newdy=dy;
		//player is in index 2 (for testing)

		// for(int i = 0;i<boundaries.size();i++){

		//System.out.println(units.get(x).getClass());
		//System.out.println(einsteinsHitbox.get(2)[0] + " vs " + units.get(x).getBounds()[0]);

for (int x=0;x<units.size();x++) {
     //   int x=6;

	if (einsteinsHitbox.get(4)[0] + sensorRange < units.get(x).getBounds()[0]
			|| einsteinsHitbox.get(4)[0] - sensorRange > units.get(x).getBounds()[0]
			|| einsteinsHitbox.get(4)[1] + sensorRange < units.get(x).getBounds()[1]
			|| einsteinsHitbox.get(4)[1] - sensorRange > units.get(x).getBounds()[1]
			) {
	    newdx=0;
	    newdy=0;
		//System.out.println(units.get(x)+ " "+ x);
		//System.out.println(einsteinsHitbox.get(4)[0] + " vs " + units.get(x).getBounds()[0]);
	} else {
		if (einsteinsHitbox.get(4)[0] < units.get(x).getBounds()[0]) {
			newdx = -speed;
		} else if (einsteinsHitbox.get(4)[0] >= units.get(x).getBounds()[0]) {
			newdx = speed;
		}
		if (einsteinsHitbox.get(4)[1] < units.get(x).getBounds()[1]) {
			newdy = -speed;
		} else if (einsteinsHitbox.get(4)[1] >= units.get(x).getBounds()[1]) {
			newdy = speed;
		}
	}
	dx= newdx;
	dy = newdy;
}}
//	}
    public void wallSensors() {
        newdx = dx;
        newdy = dy;

//checks if einstein is close to walls
            if (einsteinsHitbox.get(4)[1] + wallSensorRange > roomEntities.get(0).getLocation()[1]){
                newdy=-speed*3;
                newdx=speed;
                //System.out.println("N");
            }
        if (einsteinsHitbox.get(4)[0] - wallSensorRange < 32){
            newdx=speed*3;
            newdy=speed;
                //System.out.println("W");
        }
        if (einsteinsHitbox.get(4)[1] -  wallSensorRange < 32){
            newdy=speed*3;
            newdx=-speed;
                //System.out.println("S");
        }
        if (einsteinsHitbox.get(4)[0] + wallSensorRange > roomEntities.get(3).getLocation()[0]){
            newdx=-speed*3;
            newdy=-speed;
                //System.out.println("E");
        }
            dx = newdx;
            dy = newdy;
    }



	public void bulletSensors() {
        boolean inRange=false;
        float tempdx = 0,tempdy=0;
        for (int x=0;x<projectiles.size();x++) {
        if (!(einsteinsHitbox.get(4)[0] + sensorRange < projectiles.get(x).getBounds()[0]
                || einsteinsHitbox.get(4)[0] - sensorRange > projectiles.get(x).getBounds()[0]
                || einsteinsHitbox.get(4)[1] + sensorRange < projectiles.get(x).getBounds()[1]
                || einsteinsHitbox.get(4)[1] - sensorRange > projectiles.get(x).getBounds()[1]
        )) {

            for (int i = 0;i<projectiles.get(x).getEntityParts().size();i++)
            if(projectiles.get(x).getEntityParts().get(i) instanceof MovingPart){
                tempdx= ((MovingPart) projectiles.get(x).getEntityParts().get(i)).getDx();
                tempdy= ((MovingPart) projectiles.get(x).getEntityParts().get(i)).getDy();
            }
            dx=-tempdy+dodgespeed;
            dy=tempdx+dodgespeed;

        }else{

        }
            //System.out.println(projectiles.size());
          //  System.out.println(inRange);

        }}

        public ProjectileDirection aimSensor(){
			float dirX = 0;
			float dirY = 0;
			float distance=0;

			float shortning,xDiff,yDiff;
			for (int x=0;x<units.size();x++) {
			if (!(einsteinsHitbox.get(4)[0] + aimSensorRange < units.get(x).getBounds()[0]
					|| einsteinsHitbox.get(4)[0] - aimSensorRange > units.get(x).getBounds()[0]
					|| einsteinsHitbox.get(4)[1] + aimSensorRange < units.get(x).getBounds()[1]
					|| einsteinsHitbox.get(4)[1] - aimSensorRange > units.get(x).getBounds()[1]
			)) {
				System.out.println(units.get(x));
				dirX = units.get(x).getBounds()[0] - einsteinsHitbox.get(4)[0];
				dirY = units.get(x).getBounds()[1] - einsteinsHitbox.get(4)[1];
				distance = (float) Math.sqrt(Math.pow(dirX,2)+Math.pow(dirY,2));

				dirX = dirX*(1/distance);
				dirY = dirY*(1/distance);
				System.out.println(dirX+" : "+dirY);

			}

			}

				return new ProjectileDirection(dirX,dirY);

		}



	public float getDX(){
		return dx;
	}

	public float getDY() {
		return dy;
	}
}
