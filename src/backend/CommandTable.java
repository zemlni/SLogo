package backend;

import java.util.HashMap;
import java.util.Map;

import backend.commands.UserCommand;
import frontend.app.FrontEndController;

/**
 * @author nikita This class is the implementation of the Command Table. It
 *         stores user-defined commands. It is accessed by the parser, and is
 *         accessed when a user-defined command is being executed. The frontend
 *         is updated accordingly.
 */
public class CommandTable implements CommandTableInterface {
	private Map<String, Command> commands;
	private FrontEndController frontEndController;

	public CommandTable(FrontEndController frontEndController) {
		commands = new HashMap<String, Command>();
		this.frontEndController = frontEndController;
	}

	/**
	 * try to get the command with the name name.
	 * 
	 * @param name
	 *            the name of the command in question
	 * @return the Command requested from the table
	 * @throws CommandException
	 *             when the command was not found in the table
	 */
	@Override
	public Command getCommand(String name) throws CommandException {
		Command ret = commands.get(name.toUpperCase());
		// TODO: print 
		System.out.println("Currnet command table:");
		System.out.println(commands);
		System.out.println("GETCOMMAND: " + name.toUpperCase() + " RESULT: " + commands.get(name.toUpperCase()).getChildren());
		if (ret == null)
			throw new CommandException(name);
		return ret;
	}

	/**
	 * add a new command to the table
	 * 
	 * @param newCommand
	 *            the new Command to be added
	 */
	@Override
	public void setCommand(Command newCommand) {
		commands.remove(newCommand.getKey().toUpperCase());
		commands.put(newCommand.getKey().toUpperCase(), newCommand);
		if (newCommand instanceof UserCommand)
			frontEndController.addCommand(newCommand);
	}
	
	public void setCommands(Map<String, Command> commands) {
		this.commands = commands;
	}
	
	public Map<String, Command> getCommands(){
		Map<String, Command> temp = new HashMap<String, Command>();
		for(String str: commands.keySet()){
			UserCommand tempCommand = (UserCommand)commands.get(str);
			UserCommand command = new UserCommand(tempCommand.getString(), tempCommand.getBackendController(), tempCommand.getInfo(), tempCommand.getArgNames(),
					tempCommand.getCommands());
			temp.put(str, command);
		}
		return temp;
	}

	public void removeCommand(String key) {
		commands.remove(key.toUpperCase());

	}

}
