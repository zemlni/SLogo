package frontend.commands;

import java.util.Map;

import backend.Command;
import frontend.IViewConfig;

public class CommandsConfig implements IViewConfig {

	private static final long serialVersionUID = -6352900733191672742L;
	private Map<String, Command> commands;
	
	public CommandsConfig(Map<String, Command> commands) {
		this.commands = commands;
	}
	
	public Map<String, Command> getCommands() {
		return commands;
	}
}
