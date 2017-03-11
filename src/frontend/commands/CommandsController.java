package frontend.commands;

import java.util.HashMap;
import java.util.Map;
import backend.Command;
import backend.commands.UserCommand;
import frontend.IViewController;
import frontend.frontend.FrontEndController;
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
		commands = new HashMap<String, CommandEntry>();
	}
	public CommandsController(CommandsView view, Map<String, Command> commands) {
		this(view);
		for (String cmdKey : commands.keySet()) {
			addCommand((UserCommand) commands.get(cmdKey));
		}
	}
	
	public void setFrontEndController(FrontEndController frontEndController){
		frontEnd =frontEndController;
	}
	

	public void addCommand(UserCommand command) {
		if(commands.containsKey(command.getKey())){
			commands.get(command.getKey()).updateCommand(command);
		}
		else{
			CommandEntry commandEntry = new CommandEntry(command);
			commandsBox.getChildren().add(commandEntry);
			commands.put(command.getKey(), commandEntry);
		}
	}


	public void removeCommand(UserCommand command) throws Exception {
		String targetCommand = command.getKey();
		
		if(commands.containsKey(targetCommand)){
			for(int i = 0; i < commandsBox.getChildren().size(); i++){
				if(targetCommand.equals(commandsBox.getChildren().get(i))){
					commandsBox.getChildren().remove(i);
					commands.remove(targetCommand);
				}
			}
		}
	}
	

	public Map<String, Command> getCommands() {
		Map<String, Command> commandsRes = new HashMap<>();
		for (String cmdKey : commands.keySet()) {
			commandsRes.put(cmdKey.toUpperCase(), commands.get(cmdKey).getCommand());
		}
		return commandsRes;
	}
		
	public void clear(){
		commandsBox = new VBox();
	}
	
}
