package backend;

import java.util.HashMap;
import java.util.Map;
import backend.commands.UserCommand;
import frontend.frontend.FrontEndController;

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
			frontEndController.addCommand((UserCommand) newCommand);
	}
	
	public Map<String, Command> getCommands(){
		return commands;
	}
	
	public void setCommands(Map<String, Command> commands){
		this.commands = commands;
		frontEndController.clearCommands();
		for (String key: commands.keySet()){
			if(commands.get(key) instanceof UserCommand){
				frontEndController.addCommand((UserCommand) commands.get(key));
			}
		}
	}

	public void removeCommand(String key) {
		commands.remove(key.toUpperCase());

	}

}
