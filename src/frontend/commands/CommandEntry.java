package frontend.commands;

import backend.Command;
import backend.commands.UserCommand;
import javafx.scene.control.Button;

public class CommandEntry extends Button {
	private UserCommand userCommand;
	private String commandName;
	
	
	public CommandEntry(UserCommand command){
		super(command.getKey());
		setOnMouseClicked(e -> callCommand());
		this.getStyleClass().add("table-entry");
		commandName = command.getKey();
		userCommand = command;
	}
	
	private void callCommand(){
		userCommand.execute();
	}
	
	public String getName(){
		return commandName;
	}
	public Command getCommand() {
		return userCommand;
	}
	
	public void updateCommand(UserCommand command){
		userCommand = command;
	}
}
