package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.UserCommandInterface;
import backend.Variable;
import backend.parser.Expression;
import backend.parser.Input;

public class UserCommand extends Command implements UserCommandInterface {
	private String name;
	private Expression commands;
	private String[] variables;

	public UserCommand(String name, String[] variables, Expression commands, BackendController controller,  Input in) {
		// add command to CommandTable
		super(in, controller, variables.length);
		this.name = name;
		this.commands = commands;
		this.variables = variables;
		System.out.println("MADECOMMAND: " + name);
	}

	/**
	 * execute this user defined command
	 * 
	 * @return the return value of the last executed command.
	 */
	@Override
	public double execute() {
		System.out.println("EXECUTING USER COMMAND: " + name);
		List<Variable> vars = getArgs();
		for (int i = 0; i < vars.size(); i++) {
			vars.get(i).setKey(variables[i].substring(1));
			getBackendController().setVariable(vars.get(i));
		}
		return commands.evaluate().getValue();
	}

	/*
	 * @Override public void update(String newCommand) { this.commands =
	 * newCommand; }
	 */

	@Override
	public String getKey() {
		return name;
	}

}
