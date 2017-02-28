package expressions;

import java.util.ArrayList;
import java.util.List;

import backend.Parser;
import backend.Variable;

public class ConstantExpression extends Expression {

	public ConstantExpression(Parser parser) {
		super(parser);
	}

	@Override
	public List<Variable> parse(String[] expr, int i, double retVal) {
		List<Variable> ret = new ArrayList<Variable>();
		ret.add(new Variable(null, Double.parseDouble(expr)));
		return ret;
	}

}
