package backend.expressions;

import java.util.ArrayList;
import java.util.List;
import backend.Command;
import backend.Parser;
import backend.Variable;

public class ConstantExpression extends Expression {

	public ConstantExpression(Parser parser, Command cur) {
		super(parser, cur);
	}

	@Override
	public List<Variable> parse(String[] expr, int i, double retVal) {
		List<Variable> ret = new ArrayList<Variable>();
		ret.add(new Variable(null, Double.parseDouble(expr[i])));
		return ret;
	}

}
