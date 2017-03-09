package backend.turtle;

public class TurtleComputationsUnit {
	public TurtleComputationsUnit(){
		
	}
	
	public double calcNewXCoor(double direction, double oldX, double traveled){
		double deltaX = traveled * Math.sin(Math.toRadians(direction));
		return oldX + deltaX;
	}
	
	public double calcNewYCoor(double direction, double oldY, double traveled){
		double deltaY = traveled * Math.cos(Math.toRadians(direction));
		return oldY + deltaY;
	}
	
	public double calcDistanceFromHome(double x, double y){
		double distanceTraveled = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		return distanceTraveled;
	}
}
