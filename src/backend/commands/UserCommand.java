package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.UserCommandInterface;
import backend.Variable;
import backend.parser.Input;

public class UserCommand extends Command implements UserCommandInterface {
	private String name;
	private List<Variable> argNames;

	public UserCommand(String name, BackendController controller, Input in, List<Variable> argNames) {
		super(in, controller, argNames.size());
		this.name = name;
		this.argNames = argNames;
	}

	/**
	 * execute this user defined command
	 * 
	 * @return the return value of the last executed command.
	 */
	@Override
	public double execute() {
		for (int i = 0; i < argNames.size(); i++) {
			String varName = argNames.get(i).getKey();
			getBackendController().setVariable(new Variable(varName, getChildren().get(i + 1).evaluate().getValue()));
		}
		return getChildren().get(0).evaluate().getValue();
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
