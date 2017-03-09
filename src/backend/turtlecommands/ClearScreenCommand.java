package backend.turtlecommands;

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
		List<TurtleModel> turtleList = getTurtlePool().getActiveTurtles();
		turtleList.forEach(e -> e.clearScreenAction());
		return turtleList.get(turtleList.size() - 1).getDistanceTraveledToGetHome();
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
