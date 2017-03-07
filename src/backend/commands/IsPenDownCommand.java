package backend.commands;

import backend.BackendController;
import backend.parser.Input;

public class IsPenDownCommand extends TurtleCommand{

	public IsPenDownCommand(Input in, BackendController controller) {
		super(in, controller, 0);
	}

	@Override
	public double execute() {
		if(getTurtle().penDown()){
			return 1;
		} else {
			return 0;
		}
	}
	
}
