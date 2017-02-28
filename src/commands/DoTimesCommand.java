package commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Parser;
import backend.Variable;

public class DoTimesCommand extends Command {

	public DoTimesCommand(BackendController controller) {
		super(controller, 2);
	}

	@Override
	public double execute() {
		List<Variable> args = getArgs();
		String [] info = args.get(0).getInfo().split(Parser.WHITESPACE_NEWLINE);
		int limit = Integer.parseInt(info[1]);
		int i = 1;
		double ret = 0;
		while (i <= limit){
			Variable counter = new Variable(info[0].substring(1), i);
			getBackendController().setVariable(counter);
			ret = getBackendController().getParser().parse(args.get(1).getInfo());
			i++;
		}
		return ret;
	}

}
