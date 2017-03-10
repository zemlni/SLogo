package frontend.nonfxml.config;

import frontend.nonfxml.IViewConfig;

public class FrontEndConfig implements IViewConfig {

	private static final long serialVersionUID = 2664775665885743718L;
	
	private TurtleScreenConfig turtleScreenConfig;
	private VariablesConfig variablesConfig;
	private CommandsConfig commandsConfig;
	private HistoryConfig historyConfig;
	
	public FrontEndConfig(
			TurtleScreenConfig turtleScreenConfig,
			VariablesConfig variablesConfig,
			CommandsConfig commandsConfig,
			HistoryConfig historyConfig
		) {
		this.turtleScreenConfig = turtleScreenConfig;
		this.variablesConfig = variablesConfig;
		this.commandsConfig = commandsConfig;
		this.historyConfig = historyConfig;
	}
	
	public TurtleScreenConfig getTurtleScreenConfig() {
		return turtleScreenConfig;
	}
	public VariablesConfig getVariablesConfig() {
		return variablesConfig;
	}
	public CommandsConfig getCommandsConfig() {
		return commandsConfig;
	}
	public HistoryConfig getHistoryConfig() {
		return historyConfig;
	}

}
