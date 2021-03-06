package common.data;

public class ProjectileDirection {
	private float dx, dy;
	
	
	public static final ProjectileDirection NORTH = new ProjectileDirection(0,1);
	public static final ProjectileDirection SOUTH = new ProjectileDirection(0,-1);
	public static final ProjectileDirection EAST = new ProjectileDirection(1,0);
	public static final ProjectileDirection WEST = new ProjectileDirection(-1,0);
	public static final ProjectileDirection NORTH_EAST = new ProjectileDirection(1,1);
	public static final ProjectileDirection NORTH_WEST = new ProjectileDirection(-1,1);
	public static final ProjectileDirection SOUTH_EAST = new ProjectileDirection(1,-1);
	public static final ProjectileDirection SOUTH_WEST = new ProjectileDirection(-1,-1);
	/**
	 * Convenience class for indicating a direction for a projectile.
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
	
	/**
	 * Used to get a random direction.
	 * @return a random direction.
	 */
	public static ProjectileDirection random(){
		return new ProjectileDirection((float)Math.random()*2-1,(float)Math.random()*2-1);
	}
	
	/**
	 * Returns a 90 degree clockwise rotation on a direction.
	 * @param direction the direction to rotate.
	 * @return the result of the rotation.
	 */
	public static ProjectileDirection clockwiseRotation(ProjectileDirection direction){
		float clockWiseX = direction.dy;
		float clockWiseY = -direction.dx;
		return new ProjectileDirection(clockWiseX, clockWiseY);
	}
	
	/**
	 * Returns a 90 degree counter-clockwise rotation of a direction.
	 * @param direction the direction to rotate.
	 * @return the resulting rotated direction.
	 */
	public static ProjectileDirection counterClockwiseRotation(ProjectileDirection direction){
		float counterX = -direction.dy;
		float counterY = direction.dx;
		return new ProjectileDirection(counterX, counterY);
	}
	
	/**
	 * Gets the x-component of the vector.
	 * @return a float in the interval from 0 to 1. Inclusive
	 */
	public float getVectorXComponent(){
		return dx;
	}
	/**
	 * Gets the y-component of the vector.
	 * @return a float in the interval from 0 to 1. Inclusive
	 */
	public float getVectorYComponent() {
		return dy;
	}
	
	/**
	 * Used to normalize the direction, as it is not to be used for denoting speed.
	 * @param xComp the x component of a non-normalized vector.
	 * @param yComp the y component of a non-normalized vector.
	 */
	private void applyNormalizedDeltas(float xComp, float yComp){
		dx = xComp;
		dy = yComp;
		
		if (Math.abs(dx) == Math.abs(dy)){
			dx = Math.copySign(1f,dx);
			dy = Math.copySign(1f,dy);
		}
		
		if (Math.abs(dx)>Math.abs(dy)){
			if (!(Math.abs(dx) == 1)) {
				float multiplicationFactor = 1/ Math.abs(dx);
				dx = Math.copySign(1,dx);
				dy *= multiplicationFactor;
			}
		}else{
			if (!(Math.abs(dy) == 1)) {
				float multiplicationFactor = 1/ Math.abs(dy);
				dy = Math.copySign(1,dy);
				dx *= multiplicationFactor;
			}
		}
	}
}
