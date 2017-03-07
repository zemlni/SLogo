package frontend.app;

import java.util.LinkedList;
import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import frontend.animation.AnimatedEvent;
import frontend.views.CommandsController;
import frontend.views.HistoryController;
import frontend.views.InputController;
import frontend.views.ScriptController;
import frontend.views.ShellController;
import frontend.views.TurtleScreenController;
import frontend.views.VariablesController;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	
	// animation
	private long prevNanos = 0;
	private AnimationTimer timer;
	private LinkedList<AnimatedEvent> eventQueue;
	private LinkedList<AnimatedEvent> firstClassEventQueue; // gets executed before eventQueue
	// and doesn't cost time to update
	
	@FXML
	private void initialize() {
		sessionLanguage = Language.getLanguage();
		turtleScreenController.setFrontEndController(this);
		shellController.setFrontEndController(this);
		
		System.out.println("changed code");
		
		scriptController.setFrontEndController(this);
		variablesController.setFrontEndController(this);
		commandsController.setFrontEndController(this);
		historyController.setFrontEndController(this);
		backendController = new BackendController(this);
		initAnimation();
		eventQueue = new LinkedList<>();
		timer.start();
	}
	
	private void initAnimation() {
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				// calculate elapsed time
				if (prevNanos == 0) {
					prevNanos = now;
					return;
				}
				long deltaNanos = now - prevNanos;
				prevNanos = now;
				double dt = deltaNanos / 1.0e9;
				
				while (!firstClassEventQueue.isEmpty()) {
					firstClassEventQueue.pollFirst().update(dt);
				}
				
				AnimatedEvent action = null;
				while (dt > 0 && !eventQueue.isEmpty()) {
					action = eventQueue.pollFirst();
					dt = action.update(dt);
				}
				if (dt == 0 && !action.isFinished()) {
					eventQueue.addFirst(action);
				}
			}
		};
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
	
	// change turtle view commands
	// turtle view
	public void moveTurtles(List<Integer> ids, List<Double> x0, List<Double> y0,
			List<Double> x1, List<Double> y1, List<Boolean> penDown) {
		// TODO
	}
	public void rotateTurtles(List<Integer> ids, List<Double> startAngles, List<Double> endAngles) {
		// TODO
	}
	public void showTurtles(List<Integer> ids) {
		// TODO
	}
	public void hideTurtles(List<Integer> ids) {
		// TODO
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
