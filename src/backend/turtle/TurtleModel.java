package backend.turtle;

import frontend.frontend.FrontEndController;

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
	private double distanceTraveled;
	private double angleTurned;
	
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
	
	public int getTurtleIDNumber(){
		return turtleIDNumber;
	}
	/*
	 * moves turtle backwards
	 */
	public void moveBackwardsAction(double distance){
		moveForwardsAction(-distance);
	}
	
	/*
	 * moves the turtle forward
	 */
	public void moveForwardsAction(double distance){
		double oldX = this.xCoor;
		double oldY = this.yCoor;

		double newX = tcu.calcNewXCoor(this.direction, oldX, distance);
		double newY = tcu.calcNewYCoor(this.direction, oldY, distance);
		updateModelViewPosition(newX, newY, oldX, oldY);
	}
	
	public void clearScreenAction(){
		double oldX = this.xCoor;
		double oldY = this.yCoor;
		returnHomeModelAndCalculateDistance(oldX, oldY);
		updateTurtleViewPosition(this.xCoor, this.xCoor, this.xCoor, this.yCoor);
		frontController.clearScreen();
	}
	
	private void returnHomeModelAndCalculateDistance(double oldX, double oldY){
		storeDistanceFromHome(tcu.calcDistanceFromHome(oldX, oldY));
		updateTurtleModelPosition(0, 0);
	}
	
	public void homeAction(){
		double oldX = this.xCoor;
		double oldY = this.yCoor;
		returnHomeModelAndCalculateDistance(oldX, oldY);
		updateTurtleViewPosition(oldX, oldY, this.xCoor, this.yCoor);
	}
	
	private void storeDistanceFromHome(double dist){
		distanceTraveled = dist;
	}
	
	public void hideAction(){
		this.visible = false;
		frontController.hideTurtle(this.turtleIDNumber);
	}
	
	public void showAction(){
		this.visible = true;
		frontController.showTurtle(this.turtleIDNumber);
	}
	
	public void penDownAction(){
		this.penDown = true;
	}
	
	public void penUpAction(){
		this.penDown = false;
	}
	
	public void leftAction(double deltaD){
		double oldDir = this.direction;
		double newDir = tcu.leftRotatedDirection(oldDir, deltaD);
		this.angleTurned = Math.abs(deltaD);
		updateModelViewDirection(oldDir, newDir);
	}
	
	private void updateModelViewDirection(double oldDir, double newDir){
		updateTurtleModelDirection(newDir);
		updateTurtleViewDirection(oldDir, newDir);
	}
	
	private void updateModelViewPosition(double newX, double newY, double oldX, double oldY){
		updateTurtleModelPosition(newX, newY);
		updateTurtleViewPosition(oldX, oldY, newX, newY);
	}
	public void rightAction(double deltaD){
		leftAction(-deltaD);
	}
	
	public void setHeadingAction(double heading){
		double oldDir = this.direction;
		double newDir = heading;
		this.angleTurned = Math.abs(newDir - oldDir);
		updateModelViewDirection(oldDir, newDir);
	}
	
	public void setPositionAction(double xPos, double yPos){
		double oldX = this.xCoor;
		double oldY = this.yCoor;
		double newX = xPos;
		double newY = yPos;
		distanceTraveled = tcu.calcDistance(oldX, oldY, newX, newY);
		updateModelViewPosition(newX, newY, oldX, oldY);
	}
	
	public void setTowardsAction(double xTow, double yTow){
		double oldDir = this.direction;
		double newDir = tcu.calcTowardsTurn(this.xCoor, this.yCoor, xTow, yTow, this.direction);
		this.angleTurned = Math.abs(newDir - oldDir);
		updateModelViewDirection(oldDir, newDir);
	}
	
	public double getAngleTurned(){
		return this.angleTurned;
	}
	
	private void updateTurtleModelDirection(double angle){
		this.direction = angle;
	}
	
	private void updateTurtleViewDirection(double oldAng, double newAng){
		frontController.rotateTurtle(this.turtleIDNumber, oldAng, newAng);
	}

	/*
	 * updates the turtle model's position values
	 */
	private void updateTurtleModelPosition(double newX, double newY){
		this.setXCoor(newX);
		this.setYCoor(newY);
	}
	
	public double getDistanceTraveled(){
		return distanceTraveled;
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
	public boolean penIsDown(){
		return penDown;
	}

	private void setXCoor(double newX){
		xCoor = newX;
	}
	
	private void setYCoor(double newY){
		yCoor = newY;
	}
	
	public boolean isVis(){
		return visible;
	}
	
	public void setPenDown(){
		this.penDown = true;
	}
	
	public void setPenUp(){
		this.penDown = false;
	}
}
