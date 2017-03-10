package frontend.app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import frontend.animation.AddCommandEvent;
import frontend.animation.AddVariableEvent;
import frontend.animation.AnimatedEvent;
import frontend.animation.AppendTextEvent;
import frontend.animation.RemoveCommandEvent;
import frontend.animation.RemoveVariableEvent;
import frontend.animation.ShowErrorEvent;
import frontend.animation.ShowTextEvent;
import frontend.animation.SynchronizedEventGroup;
import frontend.animation.turtle.AddTurtleEvent;
import frontend.animation.turtle.ClearScreenEvent;
import frontend.animation.turtle.HideTurtleEvent;
import frontend.animation.turtle.MoveTurtleEvent;
import frontend.animation.turtle.RotateTurtleEvent;
import frontend.animation.turtle.ShowTurtleEvent;
import frontend.nonfxml.FrontEndView;
import frontend.nonfxml.IViewController;
import frontend.nonfxml.view.InputView;
import frontend.views.CommandsController;
import frontend.views.HistoryController;
import frontend.views.InputController;
import frontend.views.ScriptController;
import frontend.views.ShellController;
import frontend.views.TurtleScreenController;
import frontend.views.VariablesController;
import javafx.animation.AnimationTimer;
import javafx.scene.control.TabPane;
import language.Language;


/**
 * The role of this controller is to oversee and direct all of the
 *  objects that make up the front-end of the SLogo program.
 * @author Matthew Tribby, Keping Wang
 */
public class FrontEndController implements IViewController {
	public enum EventMode {
		QUEUE, GROUP, INSTANT
	}
	
	private static String sessionLanguage;
	
	private TurtleScreenController turtleScreenController;
	private ShellController shellController;
	private ScriptController scriptController;
	private VariablesController variablesController;
	private CommandsController commandsController;
	private HistoryController historyController;
	private BackendController backendController;
	
	private TabPane inputTabPane;
	
	// animation
	private long prevNanos;
	private AnimationTimer timer;
	private LinkedList<AnimatedEvent> eventQueue;
	private LinkedList<AnimatedEvent> instantEventQueue; // gets executed before eventQueue
	private List<AnimatedEvent> eventGroupBuffer;
	private EventMode eventMode;
	
	public FrontEndController(FrontEndView view) {
		sessionLanguage = Language.getLanguage();
		
		turtleScreenController = view.getTurtleScreenController();
		shellController = view.getShellController();
		scriptController = view.getScriptController();
		variablesController = view.getVariablesController();
		commandsController = view.getCommandsController();
		historyController = view.getHistoryController();
		inputTabPane = view.getInputTabPane();
		
		// TODO set the sub controllers
		turtleScreenController.setFrontEndController(this);
		shellController.setFrontEndController(this);
		scriptController.setFrontEndController(this);
		variablesController.setFrontEndController(this);
		commandsController.setFrontEndController(this);
		historyController.setFrontEndController(this);
		initAnimation();
		timer.start();
		
		backendController = new BackendController(this);

	}
	
	
	private List<AnimatedEvent> eventReceiver() {
		return eventReceiver(eventMode);
	}
	private List<AnimatedEvent> eventReceiver(EventMode mode) {
		switch (mode) {
			case QUEUE:
				return eventQueue;
			case GROUP:
				return eventGroupBuffer;
			case INSTANT:
				return instantEventQueue;
			default:
				return eventQueue;
		}
	}
	
	private void initAnimation() {
		eventQueue = new LinkedList<>();
		instantEventQueue = new LinkedList<>();
		eventGroupBuffer = new ArrayList<>();
		eventMode = EventMode.QUEUE;
		prevNanos = 0;
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
				
				while (!instantEventQueue.isEmpty()) {
					instantEventQueue.pollFirst().update(Double.POSITIVE_INFINITY);
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
	
	
	// Switch event queue and group modes.
	/**
	 * In Queue Mode, the events are executed in order, and takes "time" to execute.
	 */
	public void switchToQueueMode() {
		if (eventMode == EventMode.GROUP) {
			putGroupToQueue();
		}
		eventMode = EventMode.QUEUE;
	}
	/**
	 * In Instant Mode, the events will be executed instantly.
	 */
	public void switchToInstantMode() {
		if (eventMode == EventMode.GROUP) {
			putGroupToQueue();
		}
		eventMode = EventMode.INSTANT;
	}
	private void putGroupToQueue() {
		if (!eventGroupBuffer.isEmpty()) {
			AnimatedEvent group = new SynchronizedEventGroup(eventGroupBuffer);
			eventQueue.add(group);
			eventGroupBuffer = new ArrayList<>();
		}
	}
	/**
	 * After event grouping is started, all events received will be put is a group,
	 * and then when {@link endEventGrouping} is called, this action group will be
	 * put into the event queue. All the animated events within a group 
	 * start together, the group finishes when all events within finish. 
	 */
	public void startEventGrouping() {
		eventMode = EventMode.GROUP;
		eventGroupBuffer = new ArrayList<AnimatedEvent>();
	}
	/**
	 * Put the event group to the event queue. Then switch to event queue mode.
	 */
	public void endEventGrouping() {
		putGroupToQueue();
		eventMode = EventMode.QUEUE;
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
		eventReceiver().add(new AddVariableEvent(variablesController, variable));
	}
	/**
	 * Removes the visual representation of a Variable that is currently shown 
	 * in the Variable window
	 * @param variable
	 */
	public void removeVariable(Variable variable) {
		eventReceiver().add(new RemoveVariableEvent(variablesController, variable));
	}
	// commands view
	/**
	 * Adds a command to the Commands Controller which keep tracks of Commands on the
	 * front-end
	 * @param command
	 */
	public void addCommand(Command command) {
		eventReceiver().add(new AddCommandEvent(commandsController, command));
	}
	/**
	 * Removes the command from the Commands Controller which keeps tracks of Commands on
	 * the front-end
	 * @param command
	 */
	public void removeCommand(Command command) {
		eventReceiver().add(new RemoveCommandEvent(commandsController, command));
	}
	
	
	// change turtle view commands
	/**
	 * Move turtle(id) from (x0, y0) to (x1, y1), 
	 * draw a line at the same time is penDown is true. 
	 */
	public void moveTurtle(int id, double x0, double y0, double x1, double y1, boolean penDown) {
		System.out.println("SSDFJEIJWAFIJWEFJWAK");
		eventReceiver().add(new MoveTurtleEvent(turtleScreenController, id, x0, y0, x1, y1, penDown));
	}
	/**
	 * Rotate turtle(id) from startAngle to endAngle. 
	 */
	public void rotateTurtle(int id, double startAngle, double endAngle) {
		eventReceiver().add(new RotateTurtleEvent(turtleScreenController, id, startAngle, endAngle));
	}
	public void addTurtle(int id) {
		eventReceiver().add(new AddTurtleEvent(turtleScreenController, id));
	}
	/**
	 * Show turtle(id).
	 */
	public void showTurtle(int id) {
		eventReceiver().add(new ShowTurtleEvent(turtleScreenController, id));
	}
	/**
	 * Hide turtle(id).
	 */
	public void hideTurtle(int id) {
		eventReceiver().add(new HideTurtleEvent(turtleScreenController, id));
	}
	/**
	 * Clears the drawing screen, resets the turtle back to initial position and gets
	 * rid of all drawn lines
	 */
	public void clearScreen() {
		eventReceiver().add(new ClearScreenEvent(turtleScreenController));
	}
	
	
	// user input view: shell view and script view
	private InputController inputController() {
		return ((InputView) inputTabPane.getSelectionModel()
				.getSelectedItem().getContent()).getController();
	}

	/**
	 * Displays an error that has occurred during the processing of a certain command/function
	 * @param errorMsg String representation of error
	 */
	public void showError(String errorMsg, String bad) {
		eventReceiver().add(new ShowErrorEvent(inputController(), errorMsg, bad));
	}
	/**
	 * Displays any text that the user may need to see
	 * @param text
	 */
	public void showText(String text) {
		eventReceiver().add(new ShowTextEvent(inputController(), text));
	}
	/**
	 * Append text to the input view
	 * @param text
	 */
	public void appendText(String text) {
		eventReceiver().add(new AppendTextEvent(inputController(), text));
	}

	public void changeSelect(int id) {
		//backendController.changeSelect(id)
	}
	
}