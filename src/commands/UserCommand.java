package commands;

import java.util.List;

import backend.Command;
import backend.UserCommandInterface;
import backend.Variable;

public class UserCommand extends Command implements UserCommandInterface {
	private String name;
	private List<Command> commands;

	public UserCommand() {

	}

	/**
	 * execute this user defined command
	 * 
	 * @return the return value of the last executed command.
	 */
	@Override
	public double execute() {
		double ret = 0;
		for (Command command : commands) {
			ret = command.execute();
		}
		return ret;
	}

	@Override
	public String getKey() {
		// TODO check if this is what we need
		return name;
	}

	@Override
	public int getNumArgs() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(String newCommand) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Variable> getArgs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNumArgs(int numArgs) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setArgs(List<Variable> vars) {
		// TODO Auto-generated method stub

	}

}
