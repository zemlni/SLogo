package commands;

import backend.BackendController;
import backend.Command;
import backend.Parser;

public class SumCommand extends Command{

	public SumCommand(Parser parser, BackendController controller) {
		super(parser, controller);
		setNumArgs(2);
	}

	@Override
	public double execute() {
		double sum = getArgs().get(0).getValue() + getArgs().get(1).getValue();
		return sum;
	}

}
