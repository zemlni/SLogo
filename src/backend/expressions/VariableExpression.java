package backend.expressions;

import java.util.ArrayList;
import java.util.List;
import backend.Command;
import backend.CommandException;
import backend.Parser;
import backend.Variable;

public class VariableExpression extends Expression {

	public VariableExpression(Parser parser, Command cur) {
		super(parser, cur);
	}

	@Override
	public List<Variable> parse(String[] expr, int i, double retVal) throws CommandException {
		List<Variable> ret = new ArrayList<Variable>();
		String varName = expr[i].substring(1);
		if (getParser().getVariableTable().contains(varName))
			ret.add(getParser().getVariableTable().getVariable(varName));
		else
			ret.add(new Variable(varName, 0));
		return ret;
	}
}
