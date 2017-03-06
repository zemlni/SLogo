package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

public class AndCommand extends Command {

	public AndCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	@Override
	public double execute() {
		for (Variable var: getArgs()){
			if (var.getValue() == 0)
				return 0;
		}
		return 1;
		//return (getArgs().get(0).getValue() != 0 && getArgs().get(1).getValue() != 0) ? 1 : 0;
	}

}
