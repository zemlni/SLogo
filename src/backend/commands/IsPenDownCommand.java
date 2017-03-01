package backend.commands;

import backend.BackendController;

public class IsPenDownCommand extends TurtleCommand{

	public IsPenDownCommand(BackendController controller) {
		super(controller, 0);
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
