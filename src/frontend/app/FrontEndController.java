package frontend.app;

import frontend.Command;
import frontend.Variable;
import frontend.views.CommandsController;
import frontend.views.HistoryController;
import frontend.views.InputController;
import frontend.views.ScriptController;
import frontend.views.ShellController;
import frontend.views.TurtleController;
import frontend.views.VariablesController;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;


/**
 * The role of this controller is to oversee and direct all of the
 *  objects that make up the front-end of the SLogo program.
 * @author Matthew Tribby, Keping Wang
 */
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
	private TabPane inputTabPane;
	
	@FXML
	private void initialize() {
		turtleController.setFrontEndController(this);
		shellController.setFrontEndController(this);
		scriptController.setFrontEndController(this);
		variablesController.setFrontEndController(this);
		commandsController.setFrontEndController(this);
		historyController.setFrontEndController(this);
	}
	
	/**
	 * Passes the string input on the command line / current
	 * script line to the back-end to be processed
	 * @param input
	 */
	public void evaluate(String input) {
		System.out.println("evaluate called for input: "+input);
		// TODO Auto-generated method stub
	}

	// variables view
	/**
	 * Will ensure that a variable is added visually to the Variable window
	 * @param variable the Variable instance to be added
	 */
	public void addVariable(Variable variable) {
		variablesController.addVariable(variable);	
	}
	/**
	 * Updates a variable that already is shown in the Variable window
	 * @param updatedVariable
	 * @throws Exception if variable that is trying to be updated does not currently
	 * exist in the front-end. This exception will be more specifically defined.
	 */
	public void updateVariable(Variable updatedVariable) throws Exception {
		variablesController.updateVariable(updatedVariable);	
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
		turtleController.moveTurtleTo(x, y);
	}
	/**
	 * Draws a line which is useful for tracking the turtle's/pen's movement
	 * @param x0 starting x
	 * @param y0 starting y
	 * @param x1 ending x
	 * @param y1 ending y
	 */
	public void drawLine(double x0, double y0, double x1, double y1) {
		turtleController.drawLine(x0, y0, x1, y1);
	}
	/**
	 * Sets the new angle of the turtle/pen. This determines how the turtle/pen will move
	 * around the screen.
	 * @param angle Angle specified in degrees
	 */
	public void setTurtleAngle(double angle) {
		turtleController.setTurtleAngle(angle);
	}
	/**
	 * Clears the drawing screen, resets the turtle back to initial position and gets
	 * rid of all drawn lines
	 */
	public void clearScreen() {
		turtleController.clearScreen();
	}

	// user input view: shell view and script view
	private InputController inputController() {
		// TODO: there is a piece of code depending on the relative
		// order of the shell tab and script tab.
		if (inputTabPane.getSelectionModel().getSelectedIndex() == 0) {
			return shellController;
		} else {
			return scriptController;
		}
	}
	/**
	 * Displays an error that has occurred during the processing of a certain command/function
	 * @param errorMsg String representation of error
	 */
	public void showError(String errorMsg) {
		inputController().showError(errorMsg);
	}
	/**
	 * Displays any text that the user may need to see
	 * @param text
	 */
	public void showText(String text) {
		inputController().showText(text);
	}
	
}
