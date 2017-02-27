package backend;

import java.util.HashMap;

import commands.UserCommand;

public class CommandTable implements CommandTableInterface {
	private HashMap<String, UserCommand> commands;

	public CommandTable() {
		commands = new HashMap<String, UserCommand>();
	}

	@Override
	public UserCommand getCommand(String name) throws CommandException {
		UserCommand ret = commands.get(name.toUpperCase());
		if (ret == null)
			throw new CommandException(name);
		return ret;
	}

	@Override
	public void setCommand(UserCommand command) {
		commands.remove(command.getKey().toUpperCase());
		commands.put(command.getKey().toUpperCase(), command);
	}

	@Override
	public void removeCommand(String key) {
		commands.remove(key.toUpperCase());

	}

}
