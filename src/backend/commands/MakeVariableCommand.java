package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

public class MakeVariableCommand extends Command {

	public MakeVariableCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	@Override
	public double execute() {
		List<Variable> args = getArgs();
		Variable newVar = new Variable(args.get(0).getKey(), args.get(1).getValue());
		getBackendController().setVariable(newVar);
		return newVar.getValue();
	}
}
