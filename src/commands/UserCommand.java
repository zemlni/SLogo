package commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.UserCommandInterface;
import backend.Variable;

public class UserCommand extends Command implements UserCommandInterface {
	private String name;
	String commands;
	String [] variables;

	public UserCommand(String name, String [] variables, String commands, BackendController controller) {
		//add command to CommandTable
		super(controller);
		this.name = name;
		this.commands = commands;
		this.variables = variables;
	}


	/**
	 * execute this user defined command
	 * 
	 * @return the return value of the last executed command.
	 */
	@Override
	public double execute() {
		List<Variable> vars = getArgs();
		for (int i = 0; i < vars.size(); i++){
			vars.get(i).setKey(variables[i].substring(1));
			getBackendController().setVariable(vars.get(i));
		}
		return getBackendController().getParser().parse(commands);
	}
	

	@Override
	public void update(String newCommand) {
		this.commands = newCommand;
	}

	@Override
	public String getKey() {
		return name;
	}

}
