package backend;

import java.util.HashMap;

import backend.commands.UserCommand;
import frontend.app.FrontEndController;

public class CommandTable implements CommandTableInterface {
	private HashMap<String, Command> commands;
	private FrontEndController frontEndController;

	public CommandTable(FrontEndController frontEndController) {
		commands = new HashMap<String, Command>();
		this.frontEndController = frontEndController;
	}

	@Override
	public Command getCommand(String name) throws CommandException {
		Command ret = commands.get(name.toUpperCase());
		System.out.println("GETCOMMAND: " + name.toUpperCase() + " RESULT: " + commands.get(name.toUpperCase()));
		if (ret == null)
			throw new CommandException(name);
		return ret;
	}

	@Override
	public void setCommand(Command newCommand) {
		System.out.println("SETCOMMAND: " + newCommand.getKey());
		commands.remove(newCommand.getKey().toUpperCase());
		commands.put(newCommand.getKey().toUpperCase(), newCommand);
		if (newCommand instanceof UserCommand)
			frontEndController.addCommand(newCommand);
	}

	@Override
	public void removeCommand(String key) {
		commands.remove(key.toUpperCase());

	}

}
