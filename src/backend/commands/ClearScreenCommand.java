package backend.commands;

import backend.BackendController;
import backend.parser.Input;

public class ClearScreenCommand extends TurtleCommand{
	public ClearScreenCommand(Input in, BackendController controller){
		super(in, controller, 0);
	}

	@Override
	public double execute() {
		updateTurtle();
		return calculateDistanceTraveled();
	}
	
	private double calculateDistanceTraveled(){
		double oldX = getTurtle().getXCoor();
		double oldY = getTurtle().getYCoor();
		
		double distanceTraveled = Math.sqrt(Math.pow(oldX, 2) + Math.pow(oldY, 2));
		return distanceTraveled;
	}
	
	private void updateTurtle(){
		updateTurtleModel();
		updateTurtleView();
	}
	
	private void updateTurtleModel(){
		getTurtle().setXCoor(0);
		getTurtle().setYCoor(0);
	}
	
	private void updateTurtleView(){
		getTurtle().getFrontController().moveTurtleTo(0, 0);
		getTurtle().getFrontController().clearScreen();
	}
}
