package expressions;

import java.util.List;

import backend.CommandException;
import backend.Parser;
import backend.Variable;

public abstract class Expression {
	private Parser parser;

	public Expression(Parser parser) {
		this.parser = parser;
	}

	public abstract List<Variable> parse(String[] expr, int i, double retVal) throws CommandException;

	public Parser getParser() throws CommandException {
		return parser;
	}

	public boolean isDefinedCommand(String name) {
		return parser.isDefinedCommand(name);
	}
}
