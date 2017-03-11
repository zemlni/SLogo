package backend.commands;

import backend.BackendController;
import backend.parser.Input;

public class IDCommand extends TurtleCommand{
	public IDCommand(Input in, BackendController controller) {
		super(in, controller, 0);
	}
	
	@Override
	public double execute(){
		return getTurtlePool().getCurrentActiveTurtleID();
	}
}
