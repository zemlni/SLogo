package backend.commands;

import backend.BackendController;
import backend.Variable;
import backend.parser.Input;

public class IsShowingCommand extends TurtleCommand{

	public IsShowingCommand(Input in, BackendController controller) {
		super(in, controller, 0);
	}

	@Override
	public double execute() {
		boolean visible = true;
		for(Variable var: getArgs()){
			visible = getTurtlePool().getTurtle((int)var.getValue()).isVis();
		}
		if(visible){
			return 1;
		} else {
			return 0;
		}
	}

}
