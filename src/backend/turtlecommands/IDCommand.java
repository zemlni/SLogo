package backend.turtlecommands;

import backend.BackendController;
import backend.parser.Input;

public class IDCommand extends TurtleCommand{
	public IDCommand(Input in, BackendController controller) {
		super(in, controller, 0);
	}
	
	@Override
	public double execute(){
		System.out.println("hi");
		return getTurtlePool().getCurrentActiveTurtleID();
	}
}
