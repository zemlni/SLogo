package frontend.views;

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

	private FrontEndController frontEnd;
	public void setFrontEndController(FrontEndController frontEnd) {
		this.frontEnd = frontEnd;
	}
	
	public void addCommand(Command command) {
		commandsBox.getChildren().add(new CommandEntry(frontEnd, (UserCommand) command));
	}

	public void removeCommand(Command command) throws Exception {
		
	}
	
}
