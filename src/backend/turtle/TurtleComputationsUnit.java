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
	
	public double calcDistance(double x1, double y1, double x2, double y2){
		double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
		return distance;
	}
	
	public double leftRotatedDirection(double oldD, double deltaD) {
		return oldD - deltaD;
	}
	public double rightRotatedDirection(double oldD, double deltaD) {
		return oldD + deltaD;
	}
	public double calcTowardsTurn(double x, double y, double towX, double towY, double dir) {
		double currX = x;
		double currY = y;
		
		double atan = Math.toDegrees(Math.atan((currX - towX) / (currY - towY)));
		
		if (currX == towX && towY > currY) {
			return 0;
		} else if (currX == towX && towY < currY) {
			return 180;
		} else if (currY == towY && towX > currX) {
			return 90;
		} else if (currY == towY && towX < currX) {
			return 270;
		}
		if (towY > currY) {
			return 0 + atan;
		} else if (towY < currY) {
			return 180 + atan;
		} 

		else {
			return dir;
		}

	}
}
