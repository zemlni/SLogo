package backend.turtlecommands;

import backend.BackendController;
import backend.parser.Input;

public class TurtlesCommand extends TurtleCommand{
	public TurtlesCommand(Input in, BackendController controller) {
		super(in, controller, 0);
	}
	
	@Override
	public double execute(){
		System.out.println("hi");
		return getTurtlePool().getTotalTurtles();
	}
}
