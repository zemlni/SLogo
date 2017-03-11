package backend.commands;

import backend.BackendController;
import backend.Variable;
import backend.parser.Input;

public class IsPenDownCommand extends TurtleCommand{

	public IsPenDownCommand(Input in, BackendController controller) {
		super(in, controller, 0);
	}

	@Override
	public double execute() {
		boolean pendown = true;
		for(Variable var: getArgs()){
			pendown = getTurtlePool().getTurtle((int)var.getValue()).penIsDown();
		}
		if(pendown){
			return 1;
		} else {
			return 0;
		}
	}
	
}
