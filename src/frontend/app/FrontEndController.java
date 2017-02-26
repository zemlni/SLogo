package frontend.app;

import frontend.views.CommandsController;
import frontend.views.HistoryController;
import frontend.views.ScriptController;
import frontend.views.ShellController;
import frontend.views.TurtleController;
import frontend.views.VariablesController;
import javafx.fxml.FXML;

public class FrontEndController {
	
	@FXML
	private TurtleController turtleController;
	@FXML
	private ShellController shellController;
	@FXML
	private ScriptController scriptController;
	@FXML
	private VariablesController variablesController;
	@FXML
	private CommandsController commandsController;
	@FXML
	private HistoryController historyController;
	
	@FXML
	private void initialize() {
		turtleController.setFrontEndController(this);
		shellController.setFrontEndController(this);
		scriptController.setFrontEndController(this);
		variablesController.setFrontEndController(this);
		commandsController.setFrontEndController(this);
		historyController.setFrontEndController(this);
	}
	
}
