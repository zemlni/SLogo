package frontend.views;

import java.util.HashMap;
import java.util.Map;

import backend.Command;
import backend.commands.UserCommand;
import frontend.app.FrontEndController;
import frontend.commands.CommandEntry;
import frontend.nonfxml.IViewController;
import frontend.nonfxml.view.CommandsView;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

/**
 * THIS CLASS CAN ONLY TAKE USER COMMANDS 
 * The goal of this class is to control the Commands window which show the 
 * commands that are saved and can be executed. This commands are basically functions created
 * by the user.
 * @author Matthew Tribby
 */
public class CommandsController implements IViewController {

	
	private VBox commandsBox;
	private Map<String, CommandEntry> commands;
	private FrontEndController frontEnd;
	
	public CommandsController(CommandsView view) {
		commandsBox = view.getCommandsBox();
	}
	
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

	/**
	 * CAN ONLY TAKE USER COMMANDS NEED TO UPDATE THIS API!!!!!!!
	 * @param command
	 * @throws Exception
	 */
	public void removeCommand(Command command) throws Exception {
		UserCommand userCommand = (UserCommand) command;
		String targetCommand = userCommand.getKey();
		
		if(commands.containsKey(targetCommand)){
			for(int i = 0; i < commandsBox.getChildren().size(); i++){
				if(targetCommand.equals(commandsBox.getChildren().get(i))){
					commandsBox.getChildren().remove(i);
					commands.remove(targetCommand);
				}
			}
		}
	}
	
}
