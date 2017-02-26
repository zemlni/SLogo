package commands;

import backend.Command;
import backend.Parser;

public class SumCommand extends Command{

	public SumCommand(Parser parser) {
		super(parser);
		setNumArgs(2);
	}

	@Override
	public double execute() {
		double sum = getArgs().get(0).getValue() + getArgs().get(1).getValue();
		
		System.out.println(sum);
		return sum;
	}

}
