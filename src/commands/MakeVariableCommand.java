package commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;

public class MakeVariableCommand extends Command {

	public MakeVariableCommand(BackendController controller) {
		super(controller);
		setNumArgs(2);
	}

	@Override
	public double execute() {
		List<Variable> args = getArgs();
		Variable newVar = new Variable(args.get(0).getKey(), args.get(1).getValue());
		getBackendController().setVariable(newVar);
		return newVar.getValue();
	}

}
