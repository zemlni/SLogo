package frontend.app;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import frontend.views.CommandsController;
import frontend.views.HistoryController;
import frontend.views.InputController;
import frontend.views.ScriptController;
import frontend.views.ShellController;
import frontend.views.TurtleScreenController;
import frontend.views.VariablesController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import language.Language;
import javafx.scene.control.TabPane;
import language.Language;



/**
 * The role of this controller is to oversee and direct all of the
 *  objects that make up the front-end of the SLogo program.
 * @author Matthew Tribby, Keping Wang
 */
public class FrontEndController {

	
	private static String sessionLanguage;
	
	@FXML
	private TurtleScreenController turtleScreenController;
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
	private BackendController backendController;
	
	@FXML
	private TabPane inputTabPane;
	
	@FXML
	private void initialize() {
		sessionLanguage = Language.getLanguage();
		turtleScreenController.setFrontEndController(this);
		shellController.setFrontEndController(this);
		scriptController.setFrontEndController(this);
		variablesController.setFrontEndController(this);
		commandsController.setFrontEndController(this);
		historyController.setFrontEndController(this);
		backendController = new BackendController(this);
	}
	
	/**
	 * Passes the string input on the command line / current
	 * script line to the back-end to be processed
	 * @param input
	 */
	public void evaluate(String input) {
		if (!sessionLanguage.equals(Language.getLanguage())) {
			sessionLanguage = Language.getLanguage();
			backendController.setLanguage(sessionLanguage);
		}
		historyController.addHistory(input);
		backendController.evaluate(input);
	}

	// variables view
	/**
	 * Will ensure that a variable is added visually to the Variable window. If the variable
	 * is already present then it will update the current variable to reflect the new value
	 * @param variable the Variable instance to be added
	 */
	public void addVariable(Variable variable) {
		variablesController.addVariable(variable);	
	}

	/**
	 * Removes the visual representation of a Variable that is currently shown 
	 * in the Variable window
	 * @param variable
	 * @throws Exception if variable that is trying to be removed does not currently
	 * exist in the front-end. This exception will be more specifically defined.
	 */
	public void removeVariable(Variable variable) throws Exception {
		variablesController.removeVariable(variable);
	}

	// commands view
	/**
	 * Adds a command to the Commands Controller which keep tracks of Commands on the
	 * front-end
	 * @param command
	 */
	public void addCommand(Command command) {
		commandsController.addCommand(command);
	}
	/**
	 * Removes the command from the Commands Controller which keeps tracks of Commands on
	 * the front-end
	 * @param command
	 * @throws Exception if command that is trying to be removed doesn't exist
	 */
	public void removeCommand(Command command) throws Exception {
		commandsController.removeCommand(command);	
	}
	
	// turtle view
	/**
	 * Moves the turtle (or visual point of pen) to a certain specified location
	 * @param x new x-coordinate
	 * @param y new y-coordinate
	 */
	public void moveTurtleTo(double x, double y) {
		turtleScreenController.moveTurtleTo(x, y);
	}
	/**
	 * Draws a line which is useful for tracking the turtle's/pen's movement
	 * @param x0 starting x
	 * @param y0 starting y
	 * @param x1 ending x
	 * @param y1 ending y
	 */
	public void drawLine(double x0, double y0, double x1, double y1) {
		//Test line:
		//backendController.setVariable(new Variable("test", 15 ));
		turtleScreenController.drawLine(x0, y0, x1, y1);
	}
	/**
	 * Sets the new angle of the turtle/pen. This determines how the turtle/pen will move
	 * around the screen.
	 * @param angle Angle specified in degrees
	 */
	public void setTurtleAngle(double angle) {
		turtleScreenController.setTurtleAngle(angle);
	}
	
	/**
	 * Shows the turtle image visibly. If the turtle is already showing, this will 
	 * have no effect
	 */
	public void showTurtle(){
		turtleScreenController.showTurtle();
	}
	
	/**
	 * Hides the turtle image visibly. If the turtle is already hidden, this will
	 * have no effect
	 */
	public void hideTurtle(){
		turtleScreenController.hideTurtle();
	}
	
	
	/**
	 * Clears the drawing screen, resets the turtle back to initial position and gets
	 * rid of all drawn lines
	 */
	public void clearScreen() {
		turtleScreenController.clearScreen();
	}

	// user input view: shell view and script view
	private InputController inputController() {
		// TODO: there is a piece of code depending on the relative
		// order of the shell tab and script tab.
		if (inputTabPane.getSelectionModel().getSelectedIndex() == 1) {
			return shellController;
		} else {
			return scriptController;
		}
	}
	/**
	 * Might be called in input controllers to show error in alert window.
	 * @param errorMsg
	 */
	public void showErrorAlert(String errorMsg, String bad) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(Language.getWord("ErrorTitle"));
		alert.setContentText(Language.getWord(errorMsg) + bad);
		alert.showAndWait();
	}
	/**
	 * Displays an error that has occurred during the processing of a certain command/function
	 * @param errorMsg String representation of error
	 */
	public void showError(String errorMsg, String bad) {
		inputController().showError(errorMsg, bad);
	}
	/**
	 * Displays any text that the user may need to see
	 * @param text
	 */
	public void showText(String text) {
		System.out.println("show text: "+text);
		inputController().showText(text);
	}
	/**
	 * Append text to the input view
	 * @param text
	 */
	public void appendText(String text) {
		inputController().appendText(text);
	}
	
}
