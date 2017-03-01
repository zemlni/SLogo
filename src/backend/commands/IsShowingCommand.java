package backend.commands;

import backend.BackendController;

public class IsShowingCommand extends TurtleCommand{

	public IsShowingCommand(BackendController controller) {
		super(controller, 0);
	}

	@Override
	public double execute() {
		if(getTurtle().isVis()){
			return 1;
		}else{
			return 0;
		}
	}

}
