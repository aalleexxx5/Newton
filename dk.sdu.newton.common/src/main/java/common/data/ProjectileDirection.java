package common.data;

public class ProjectileDirection {
	private float dx, dy;
	
	
	public static final ProjectileDirection NORTH = new ProjectileDirection(0,-1);
	public static final ProjectileDirection SOUTH = new ProjectileDirection(0,1);
	public static final ProjectileDirection EAST = new ProjectileDirection(1,0);
	public static final ProjectileDirection WEST = new ProjectileDirection(-1,0);
	public static final ProjectileDirection NORTH_EAST = new ProjectileDirection(1,-1);
	public static final ProjectileDirection NORTH_WEST = new ProjectileDirection(-1,-1);
	public static final ProjectileDirection SOUTH_EAST = new ProjectileDirection(1,1);
	public static final ProjectileDirection SOUTH_WEST = new ProjectileDirection(-1,1);
	/**
	 * Convienience class for indicating a direction for a projectile.
	 * @param vectorXComponent the x component of a direction vector. Must be between 0 and 1.
	 * @param vectorYComponent the y component of a direction vector. Must be between 0 and 1.
	 */
	public ProjectileDirection(float vectorXComponent, float vectorYComponent){
		if (vectorXComponent >1|| vectorXComponent < -1 || vectorYComponent > 1 ||vectorYComponent< -1){
			throw new IllegalArgumentException("Projectile direction is used to determine a direction, and not a speed. Thus the vectors need to be normalized");
		}
		if (vectorXComponent == 0 && vectorYComponent == 0){
			dx = 0;
			dy = 0;
		}else{
			applyNormalizedDeltas(vectorXComponent, vectorYComponent);
		}
	}
	
	public float getVectorXComponent(){
		return dx;
	}
	
	public float getVectorYComponent() {
		return dy;
	}
	
	private void applyNormalizedDeltas(float xComp, float yComp){
		dx = xComp;
		dy = yComp;
		if (dx>=dy){
			if (!(dx == 1)) {
				float multiplicationFactor = 1/dx;
				dx = 1f;
				dy *= multiplicationFactor;
			}
		}else{
			float multiplicationFactor = 1/dy;
			dy = 1f;
			dx *= multiplicationFactor;
		}
	}
}
