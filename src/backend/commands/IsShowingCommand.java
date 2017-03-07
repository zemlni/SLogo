package backend.commands;

import backend.BackendController;
import backend.parser.Input;

public class IsShowingCommand extends TurtleCommand{

	public IsShowingCommand(Input in, BackendController controller) {
		super(in, controller, 0);
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
