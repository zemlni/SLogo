package backend.expressions;

import java.util.ArrayList;
import java.util.List;
import backend.Command;
import backend.CommandException;
import backend.Parser;
import backend.Variable;
import backend.commands.MakeUserInstructionCommand;

public class CommandExpression extends Expression {

	public CommandExpression(Parser parser, Command cur) {
		super(parser, cur);
	}

	@Override
	public List<Variable> parse(String[] split, int i, double retVal) throws CommandException{
		List<Variable> ret = new ArrayList<Variable>();
		if (isDefinedCommand(split[i]) && !(getCommand() instanceof MakeUserInstructionCommand)) {
			double[] recurse = getParser().parse(split, i, retVal);
			ret.add(new Variable(null, recurse[0]));
			ret.add(new Variable(null, recurse[1] - i));
		} else
			ret.add(new Variable(split[i], 0));
		return ret;
	}
}
