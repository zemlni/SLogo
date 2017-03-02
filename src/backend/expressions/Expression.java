package backend.expressions;

import java.util.List;
import backend.Command;
import backend.CommandException;
import backend.Parser;
import backend.Variable;

public abstract class Expression {
	private Parser parser;
	private Command command;

	public Expression(Parser parser, Command cur) {
		this.parser = parser;
		this.command = cur;
	}

	public abstract List<Variable> parse(String[] expr, int i, double retVal) throws Exception;

	public Parser getParser(){
		return parser;
	}
	public Command getCommand(){
		return command;
	}

	private boolean isDefinedLangCommand(String name) {
		try {
			getParser().getCommandSymbol(name);
			return true;
		} catch (CommandException e) {
			return false;
		}
	}

	private boolean isDefinedUserCommand(String name) {
		try {
			parser.getCommandTable().getCommand(name);
			return true;
		} catch (CommandException e) {
			return false;
		}
	}

	public boolean isDefinedCommand(String name) {
		return isDefinedUserCommand(name) || isDefinedLangCommand(name);
	}
}
