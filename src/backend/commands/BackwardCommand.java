package backend.commands;
import java.util.List;

import backend.BackendController;
import backend.Variable;
import backend.parser.Input;
import backend.turtle.TurtleModel;

public class BackwardCommand extends TurtleCommand{

	public BackwardCommand(Input in, BackendController controller){
		super(in, controller, 1);
	}
	
	@Override
	public double execute(){
		double backwardAmount = 0;
		getTurtlePool().getFrontController().startEventGrouping();
		List<TurtleModel> turtles = getTurtlePool().getCommandableTurtleModels();
		for(TurtleModel t :turtles){
			getTurtlePool().setCurrentActiveTurtle(t.getTurtleIDNumber());
			for(Variable var: getArgs()){
				backwardAmount = var.getValue();
				t.moveBackwardsAction(backwardAmount);
			}
		}
		getTurtlePool().getFrontController().endEventGrouping();

		return backwardAmount;
	}

	/*
	 * executes the function of the command.
	 */
//	@Override
//	public double execute() {
//		double backwardAmount = 0;
//		for (Variable var: getArgs()){
//			backwardAmount = var.getValue();
//			moveTurtle(backwardAmount);
//		}
////		double backwardAmount = getArgs().get(0).getValue();
////		moveTurtle(backwardAmount);
//		return backwardAmount;
//	}

	/*
	 * moves the turtle in the model, and in the display of the turtle
	 */
//	private void moveTurtle(double traveled){
//		TurtleModel turtle = getTurtle();
//		double oldXCoor = turtle.getXCoor();
//		double oldYCoor = turtle.getYCoor();
//		double turtleDirection = turtle.getDirection();
//
//		double newXCoor = calcNewXCoor(turtleDirection, oldXCoor, traveled);
//		double newYCoor = calcNewYCoor(turtleDirection, oldYCoor, traveled);
//
//		updateTurtleModel(newXCoor, newYCoor, turtle);
//		updateTurtleView(oldXCoor, oldYCoor, newXCoor, newYCoor, turtle);
//	}
	/*
	 * makes backend calls to update the turtlemodel
	 */
//	private void updateTurtleModel(double newX, double newY, TurtleModel turtle){
//		turtle.setXCoor(newX);
//		turtle.setYCoor(newY);
//	}
//
//	/*
//	 * makes frontend calls to move the display of the turtle
//	 */
//	private void updateTurtleView(double oldX, double oldY, double newX, double newY, TurtleModel turtle){
//		FrontEndController fcontroller = turtle.getFrontController();
//		if(turtle.penDown()){
//			fcontroller.drawLine(oldX, oldY, newX, newY);
//		}
//		fcontroller.moveTurtleTo(newX, newY);
//	}

	/*
	 * calculate the new x coordinate by calc deltaX then add to oldX
	 */
//	private double calcNewXCoor(double direction, double oldX, double traveled){
//		double deltaX = traveled * Math.sin(Math.toRadians(direction));
//		return oldX - deltaX;
//	}
//
//	/*
//	 * calculate the new y coordinate by calc deltaY then add to oldY
//	 */
//	private double calcNewYCoor(double direction, double oldY, double traveled){
//		double deltaY = traveled * Math.cos(Math.toRadians(direction));
//		return oldY - deltaY;
//	}

}


