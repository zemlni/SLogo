package expressions;

import java.util.ArrayList;
import java.util.List;

import backend.Parser;
import backend.Variable;

public class ListStartExpression extends Expression {

	public ListStartExpression(Parser parser) {
		super(parser);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Variable> parse(String[] expr, int i, double retVal) {
		List<Variable> ret = new ArrayList<Variable>();
		return null;
	}

}
