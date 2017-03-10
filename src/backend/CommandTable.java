package backend;

import java.util.HashMap;
import java.util.Map;

import backend.commands.UserCommand;
import frontend.app.FrontEndController;

public class CommandTable implements CommandTableInterface {
	private Map<String, Command> commands;
	private FrontEndController frontEndController;

	public CommandTable(FrontEndController frontEndController) {
		commands = new HashMap<String, Command>();
		this.frontEndController = frontEndController;
	}

	@Override
	public Command getCommand(String name) throws CommandException {
		Command ret = commands.get(name.toUpperCase());
		// TODO: print 
		System.out.println("Currnet command table:");
		System.out.println(commands);
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
	
	public void setCommands(Map<String, Command> commands) {
		this.commands = commands;
	}
	
	public Map<String, Command> getCommands(){
		System.out.println("Commands to be saved:");
		System.out.println(commands);
		return commands;
	}

	@Override
	public void removeCommand(String key) {
		commands.remove(key.toUpperCase());

	}

}
