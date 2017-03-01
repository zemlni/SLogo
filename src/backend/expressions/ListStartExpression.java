package backend.expressions;

import java.util.ArrayList;
import java.util.List;

import backend.Command;
import backend.CommandException;
import backend.Parser;
import backend.Variable;

public class ListStartExpression extends Expression {

	public ListStartExpression(Parser parser, Command cur) {
		super(parser, cur);
	}

	@Override
	public List<Variable> parse(String[] split, int i, double retVal) throws CommandException {
		List<Variable> ret = new ArrayList<Variable>();
		int temp = 1;
		String symbol = getParser().getSyntaxSymbol(split[i + temp]);
		String arg = "";
		while (!symbol.equals("ListEnd")) {
			arg += split[i + temp] + " ";
			temp ++;
			symbol = getParser().getSyntaxSymbol(split[i + temp]);
		}
		ret.add(new Variable(arg));
		ret.add(new Variable(null, temp));
		return ret;
	}

}
