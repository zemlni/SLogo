package backend.turtle;

import frontend.app.FrontEndController;

public class TurtleModel {
	private boolean penDown;
	private FrontEndController frontController;
	
	private double xCoor;
	private double yCoor;
	// direction is in degrees. 0 points up.
	private double direction;
	private boolean visible;
	private TurtleComputationsUnit tcu;
	private int turtleIDNumber;
	private double distanceTraveledToGetHome;
	
	public TurtleModel(int id, FrontEndController controller){
		tcu = new TurtleComputationsUnit();
		frontController = controller;
		turtleIDNumber = id;
		/*
		 * TODO: Move these default values to a default file at some point
		 */
		direction = 0;
		xCoor = 0;
		yCoor = 0;
		penDown = true;
		visible = true;
	}
	/*
	 * moves turtle backwards
	 */
	public void moveBackwardsAction(double distance){
		double oldX = this.xCoor;
		double oldY = this.yCoor;
		double dir = this.direction;
		double backwardDistance = -distance;
		
		double newX = tcu.calcNewXCoor(dir, oldX, backwardDistance);
		double newY = tcu.calcNewYCoor(dir, oldY, backwardDistance);
		updateTurtleModelPosition(newX, newY);
		updateTurtleViewPosition(oldX, oldY, newX, newY);
	}
	
	/*
	 * moves the turtle forward
	 */
	public void moveForwardsAction(double distance){
		double oldX = this.xCoor;
		double oldY = this.yCoor;
		double dir = this.direction;
		double forwardDistance = distance;
		
		double newX = tcu.calcNewXCoor(dir, oldX, forwardDistance);
		double newY = tcu.calcNewYCoor(dir, oldY, forwardDistance);
		updateTurtleModelPosition(newX, newY);
		updateTurtleViewPosition(oldX, oldY, newX, newY);
	}
	
	public void clearScreenAction(){
		double oldX = this.xCoor;
		double oldY = this.yCoor;
		returnHomeModelAndCalculateDistance();
		frontController.clearScreen();
	}
	
	private void returnHomeModelAndCalculateDistance(){
		storeDistanceFromHome(tcu.calcDistanceFromHome(this.xCoor, this.yCoor));
		updateTurtleModelPosition(0, 0);
	}
	
	public void homeAction(){
		double oldX = this.xCoor;
		double oldY = this.yCoor;
		returnHomeModelAndCalculateDistance();
		updateTurtleViewPosition(oldX, oldY, this.xCoor, this.yCoor);
	}
	
	private void storeDistanceFromHome(double dist){
		distanceTraveledToGetHome = dist;
	}
	
	public void hideAction(){
		this.visible = false;
		frontController.hideTurtle(this.turtleIDNumber);
	}
	
	public void showAction(){
		this.visible = true;
		frontController.showTurtle(this.turtleIDNumber);
	}

	
	/*
	 * updates the turtle model's position values
	 */
	private void updateTurtleModelPosition(double newX, double newY){
		this.setXCoor(newX);
		this.setYCoor(newY);
	}
	
	public double getDistanceTraveledToGetHome(){
		return distanceTraveledToGetHome;
	}

	/*
	 * makes frontend calls to move the display of the turtle
	 */
	private void updateTurtleViewPosition(double x0, double y0, double x1, double y1){
		frontController.moveTurtle(this.turtleIDNumber, x0, y0, x1, y1, this.penDown);
	}
	
	

	
	
	public FrontEndController getFrontController(){
		return frontController;
	}
	
	public double getDirection(){
		return direction;
	}
	
	/*
	 * returns xCoordinate
	 */
	public double getXCoor(){
		return xCoor;
	}
	
	/*
	 * returns yCoordinate
	 */
	public double getYCoor(){
		return yCoor;
	}
	
	/*
	 * return boolean penDown, for use to check if it moving should draw or not
	 */
	public boolean penDown(){
		return penDown;
	}
	
	/*
	 * "places" the pen (penDown is true)
	 */
	public void placePen(){
		penDown = true;
	}
	
	/*
	 * "lifts" the pen (penDown is false)
	 */
	public void liftPen(){
		penDown = false;
	}
	
	public void setXCoor(double newX){
		xCoor = newX;
	}
	
	public void setYCoor(double newY){
		yCoor = newY;
	}
	
	public void setDir(double newDir){
		direction = newDir;
	}
	public boolean isVis(){
		return visible;
	}
	
	
	
}
