package backend.commands;

import backend.BackendController;
import backend.TurtleModel;
import frontend.app.FrontEndController;

public class RightCommand extends TurtleCommand{
	public RightCommand(BackendController controller){
		super(controller, 1);
	}

	@Override
	public double execute() {
		double deltaDir = getArgs().get(0).getValue();
		rotateTurtle(deltaDir);
		
		return deltaDir;
	}
	
	public void rotateTurtle(double degrees){
		TurtleModel turtle = getTurtle();
		double oldDir = turtle.getDirection();
		
		double newDir = calculateNewDir(oldDir, degrees);
		
		rotateTurtleModel(newDir, turtle);
		rotateTurtleView(newDir, turtle);
	}
	
	public void rotateTurtleModel(double newDir, TurtleModel turtle){
		turtle.setDir(newDir);
	};
	
	public void rotateTurtleView(double newDir, TurtleModel turtle){
		FrontEndController fcontroller = turtle.getFrontController();
		fcontroller.setTurtleAngle(newDir);
	};
	
	public double calculateNewDir(double oldD, double deltaD){
		return oldD + deltaD;
	}
}
