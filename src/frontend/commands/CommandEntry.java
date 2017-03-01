package frontend.commands;

import backend.commands.UserCommand;
import frontend.app.FrontEndController;
import javafx.scene.control.Button;

public class CommandEntry extends Button {
	private FrontEndController myController;
	private UserCommand userCommand;
	
	public CommandEntry(FrontEndController frontEnd, UserCommand command){
		super(command.getKey());
		setOnMouseClicked(e -> callCommand());
		this.getStyleClass().add("table-entry");
		myController = frontEnd;
		userCommand = command;
	}
	
	private void callCommand(){
		userCommand.execute();
	}
}
