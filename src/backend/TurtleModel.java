package backend;

import frontend.app.FrontEndController;

public class TurtleModel {
	private boolean penDown;
	private FrontEndController frontController;
	
	private double xCoor;
	private double yCoor;
	// direction is in degrees. 0 points up.
	private double direction;
	private boolean visible;
	
	public TurtleModel(FrontEndController controller){
		frontController = controller;
		/*
		 * TODO: Move these default values to a default file at some point
		 */
		direction = 0;
		xCoor = 0;
		yCoor = 0;
		penDown = true;
		visible = true;
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
	public void setInvis(){
		visible = false;
	}
	public void setVis(){
		visible = true;
	}
	public boolean isVis(){
		return visible;
	}
}
