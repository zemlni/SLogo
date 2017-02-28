package expressions;

import java.util.ArrayList;
import java.util.List;

import backend.Parser;
import backend.Variable;

public class CommandExpression extends Expression {

	public CommandExpression(Parser parser) {
		super(parser);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Variable> parse(String[] expr, int i, double retVal) {
		List<Variable> ret = new ArrayList<Variable>();
		if (isDefinedCommand(expr[i])) {
			double[] recurse = getParser().parse(expr, i, retVal);
			vars.add(new Variable(null, recurse[0]));
			double diff = recurse[1] - (i + 1);
			i += diff;
			index += diff;
		} else {
			ret.add(new Variable(expr[i + 1], 0));
		}
	}
}
