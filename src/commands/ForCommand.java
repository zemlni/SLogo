package commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Parser;
import backend.Variable;

public class ForCommand extends Command {

	public ForCommand(BackendController controller) {
		super(controller);
		setNumArgs(2);
	}

	@Override
	public double execute() {
		List<Variable> args = getArgs();
		String[] info = args.get(0).getKey().split(Parser.WHITESPACE_NEWLINE);
		int start = Integer.parseInt(info[1]);
		int end = Integer.parseInt(info[2]);
		int increment = Integer.parseInt(info[3]);
		int i = start;
		double ret = 0;
		while ((increment >= 0 && i < end) || (increment < 0 && i > end)) {
			Variable counter = new Variable(info[0].substring(1), i);
			getBackendController().setVariable(counter);
			ret = getBackendController().getParser().parse(args.get(1).getKey());
			i += increment;
		}
		return ret;
	}

}
