package backend.commands.abstracts;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

public abstract class SimpleMapCommand extends Command {

	public SimpleMapCommand(Input info, BackendController controller, int i) {
		super(info, controller, i);
	}
	
	@Override
	public double execute(){
		List<Variable> args = getArgs();
		double result = 0;
		for (int i = 0; i < args.size(); i++) {
			result = run(args.get(0).getValue());
		}
		return result;
	}
	
	public abstract double run(double argValue);
}
