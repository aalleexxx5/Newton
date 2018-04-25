package dk.sdu.newton.weapons.emergency;

import common.data.Entity;
import common.data.Sprite;
import common.services.Destructable;
import org.osgi.service.http.NamespaceException;

public class EmergencyTpReady extends Entity implements Destructable {
	private static final String FILENAME = "blueThinSpiral.png";
	private boolean destruct = false;
	private static final float WIDTH = 64;
	private static final float HEIGHT = 64;
	public EmergencyTpReady(float[] location) {
		this.location = location;
	}
	
	@Override
	public Sprite draw() {
		return new Sprite(FILENAME, location[0]-(WIDTH-32)/2, location[1]-(HEIGHT-32)/2, WIDTH, HEIGHT);
	}
	
	@Override
	public Sprite getSprite() {
		return new Sprite(FILENAME, 0,0, WIDTH, HEIGHT);
	}
	
	@Override
	public Boolean shouldDestruct() {
		return destruct;
	}
	
	@Override
	public void setDestruct() {
		destruct = true;
	}
}
