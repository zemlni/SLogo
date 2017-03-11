package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.parser.Input;
import backend.turtle.TurtleModel;

public class ClearScreenCommand extends TurtleCommand{
	public ClearScreenCommand(Input in, BackendController controller){
		super(in, controller, 0);
	}

	@Override
	public double execute() {
		List<TurtleModel> turtles = getTurtlePool().getCommandableTurtleModels();
		getTurtlePool().getFrontController().startEventGrouping();
		for(TurtleModel t :turtles){
			getTurtlePool().setCurrentActiveTurtle(t.getTurtleIDNumber());
			t.clearScreenAction();
		}		
		getTurtlePool().getFrontController().endEventGrouping();

		return turtles.get(turtles.size() - 1).getDistanceTraveled();
	}
	
//	private double calculateDistanceTraveled(){
//		double oldX = getTurtle().getXCoor();
//		double oldY = getTurtle().getYCoor();
//		
//		double distanceTraveled = Math.sqrt(Math.pow(oldX, 2) + Math.pow(oldY, 2));
//		return distanceTraveled;
//	}
	
//	private void updateTurtle(){
//		updateTurtleModel();
//		updateTurtleView();
//	}
//	
//	private void updateTurtleModel(){
//		getTurtle().setXCoor(0);
//		getTurtle().setYCoor(0);
//	}
//	
//	private void updateTurtleView(){
//		getTurtle().getFrontController().moveTurtleTo(0, 0);
//		getTurtle().getFrontController().clearScreen();
//	}
}
