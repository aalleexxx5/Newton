package dk.sdu.newton.einstein;

import java.util.Random;

public class EinsteinControl {

	private Einstein einstein;
	private float movement[] = new float[2];
	private Random random;

	public EinsteinControl(Einstein einstein){
		this.einstein = einstein;
	}

	public float getDX(){
		int randomInt = random.nextInt(2);
		if (randomInt == 0) {
			return 5;
		}
		else if (randomInt == 1) {
			return -5;
		}
		else {
			return 0;
		}
	}

	public float getDY() {
		return getDX();
	}
}
