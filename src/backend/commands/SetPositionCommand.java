package backend.commands;

import backend.BackendController;

public class SetPositionCommand extends TurtleCommand{

	public SetPositionCommand(BackendController controller) {
		super(controller, 2);
	}

	@Override
	public double execute() {
		double oldX = getTurtle().getXCoor();
		double oldY = getTurtle().getYCoor();
		
		double newX = getArgs().get(0).getValue();
		double newY = getArgs().get(1).getValue();
		
		updateTurtle(oldX, oldY, newX, newY);
		return calculateDistanceTraveled(oldX, oldY, newX, newY);
	}
	
	private double calculateDistanceTraveled(double oldX, double oldY, double newX, double newY){
		double distanceTraveled = Math.sqrt(Math.pow(oldX - newX, 2) + Math.pow(oldY - newY, 2));
		return distanceTraveled;
	}
	
	private void updateTurtle(double oldX, double oldY, double newX, double newY){
		updateTurtleModel(newX, newY);
		updateTurtleView(oldX, oldY, newX, newY);
	}
	
	private void updateTurtleModel(double newX, double newY){
		getTurtle().setXCoor(newX);
		getTurtle().setYCoor(newY);
	}
	
	private void updateTurtleView(double oldX, double oldY, double newX, double newY){
		getTurtle().getFrontController().moveTurtleTo(newX, newY);
		if(getTurtle().penDown()){
			getTurtle().getFrontController().drawLine(oldX, oldY, newX, newY);
		}
		getTurtle().getFrontController().moveTurtleTo(newX, newY);
	}
	
}
