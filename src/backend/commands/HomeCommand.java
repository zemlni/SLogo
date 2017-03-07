package backend.commands;

import backend.BackendController;
import backend.parser.Input;

public class HomeCommand extends TurtleCommand{

	public HomeCommand(Input in, BackendController controller) {
		super(in, controller, 0);
	}

	@Override
	public double execute() {
		double oldX = getTurtle().getXCoor();
		double oldY = getTurtle().getYCoor();
		updateTurtle(oldX, oldY);
		return calculateDistanceTraveled(oldX, oldY);
	}
	
	private double calculateDistanceTraveled(double oldX, double oldY){

		
		double distanceTraveled = Math.sqrt(Math.pow(oldX, 2) + Math.pow(oldY, 2));
		return distanceTraveled;
	}
	
	private void updateTurtle(double oldX, double oldY){
		updateTurtleModel();
		updateTurtleView(oldX, oldY);
	}
	
	private void updateTurtleModel(){
		getTurtle().setXCoor(0);
		getTurtle().setYCoor(0);
	}
	
	private void updateTurtleView(double oldX, double oldY){
		getTurtle().getFrontController().moveTurtleTo(0, 0);
		if(getTurtle().penDown()){
			getTurtle().getFrontController().drawLine(oldX, oldY, 0, 0);
		}
		getTurtle().getFrontController().moveTurtleTo(0, 0);
	}

}
