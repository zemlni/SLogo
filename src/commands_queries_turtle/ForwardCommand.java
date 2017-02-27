package commands_queries_turtle;

import backend.BackendController;
import backend.Parser;
import backend.TurtleModel;
import frontend.FrontEndController;

public class ForwardCommand extends TurtleCommand{
	
	public ForwardCommand(Parser parser, BackendController controller){
		super(parser, controller);
		setNumArgs(1);
	}
	
	/*
	 * executes the function of the command.
	 */
	@Override
	public double execute() {
		System.out.println("TEST");
		double forwardAmount = getArgs().get(0).getValue();
		
		moveTurtle(forwardAmount);
		return forwardAmount;
	}
	
	/*
	 * moves the turtle in the model, and in the display of the turtle
	 */
	private void moveTurtle(double traveled){
		TurtleModel turtle = getTurtle();
		double oldXCoor = turtle.getXCoor();
		double oldYCoor = turtle.getYCoor();
		double turtleDirection = turtle.getDirection();
		
		double newXCoor = calcNewXCoor(turtleDirection, oldXCoor, traveled);
		double newYCoor = calcNewYCoor(turtleDirection, oldYCoor, traveled);
		
		updateTurtleModel(newXCoor, newYCoor, turtle);
		updateTurtleView(oldXCoor, oldYCoor, newXCoor, newYCoor, turtle);
	}
	/*
	 * makes backend calls to update the turtlemodel
	 */
	private void updateTurtleModel(double newX, double newY, TurtleModel turtle){
		turtle.setXCoor(newX);
		turtle.setYCoor(newY);
	}
	
	/*
	 * makes frontend calls to move the display of the turtle
	 */
	private void updateTurtleView(double oldX, double oldY, double newX, double newY, TurtleModel turtle){
		FrontEndController fcontroller = turtle.getFrontController();
		if(turtle.penDown()){
			fcontroller.drawLine(oldX, oldY, newX, newY);
		}
		fcontroller.moveTurtleTo(newX, newY);
	}
	
	/*
	 * calculate the new x coordinate by calc deltaX then add to oldX
	 */
	private double calcNewXCoor(double direction, double oldX, double traveled){
		double deltaX = traveled * Math.sin(direction);
		return oldX + deltaX;
	}
	
	/*
	 * calculate the new y coordinate by calc deltaY then add to oldY
	 */
	private double calcNewYCoor(double direction, double oldY, double traveled){
		double deltaY = traveled * Math.cos(direction);
		return oldY + deltaY;
	}

}
