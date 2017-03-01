package frontend.views;

import java.util.HashMap;
import java.util.Map;

import backend.Command;
import backend.commands.UserCommand;
import frontend.app.FrontEndController;
import frontend.commands.CommandEntry;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

/**
 * The goal of this class is to control the Commands window which show the 
 * commands that are saved and can be executed. This commands are basically functions created
 * by the user.
 * @author Matthew Tribby
 */
public class CommandsController {

	@FXML
	private VBox commandsBox;
	private Map<String, CommandEntry> commands;
	private FrontEndController frontEnd;
	
	public CommandsController(){
		commands = new HashMap<String, CommandEntry>();
	}
	
	public void setFrontEndController(FrontEndController frontEndController){
		frontEnd =frontEndController;
	}
	
	public void addCommand(Command command) {
		UserCommand userCommand = (UserCommand) command;
		if(commands.containsKey(userCommand.getKey())){
			commands.get(userCommand.getKey()).updateCommand(userCommand);
		}
		else{
			CommandEntry commandEntry = new CommandEntry(userCommand);
			commandsBox.getChildren().add(commandEntry);
			commands.put(userCommand.getKey(), commandEntry);
		}
	}

	//Currently there is no need for this method so not implemented. Might remove from the 
	//API, will decide based on second set of implementation goals
	public void removeCommand(Command command) throws Exception {
		
	}
	
}
