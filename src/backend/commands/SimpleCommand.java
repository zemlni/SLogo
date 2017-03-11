package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

public abstract class SimpleCommand extends Command {

	public SimpleCommand(Input info, BackendController controller, int i) {
		super(info, controller, i);
	}
	
	public double execute(){
		List<Variable> args = getArgs();
		double result = args.get(0).getValue();
		for (int i = 1; i < args.size(); i++)
			result = run(result, args.get(i));
		return result;
	}
	
	public abstract double run(double result, Variable curArg);
}
