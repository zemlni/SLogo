package frontend;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FrontEndController implements FrontEndControllerInterface {
	//Sub-controllers
	private VariablesController variablesController;
	private CommandsController commandsController;
	private TurtleController turtleController;
	private ShellController shellController;
	private ScriptController scriptController;
	
	//Other Variables
	private Boolean scriptTab = false;
	private Group root;
	public static final int SCENE_WIDTH = 1000;
	public static final int SCENE_HEIGHT = 500;
	
	public FrontEndController(Stage stage){
		variablesController = new VariablesController();
		commandsController = new CommandsController();
		turtleController = new TurtleController();
		shellController = new ShellController();
		scriptController = new ScriptController();
		root = new Group();
		display();
		stage.setScene(new Scene(root, SCENE_WIDTH, SCENE_HEIGHT));
		stage.show();
	}
	
	@Override
	public void evaluate(String input) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addVariable(Variable variable) {
		variablesController.addVariable(variable);	
	}

	@Override
	public void updateVariable(Variable updatedVariable) throws Exception {
		variablesController.updateVariable(updatedVariable);
		
	}

	@Override
	public void removeVariable(Variable variable) throws Exception {
		variablesController.removeVariable(variable);
	}

	@Override
	public void addCommand(Command command) {
		commandsController.addCommand(command);
	}

	@Override
	public void removeCommand(Command command) throws Exception {
		commandsController.removeCommand(command);	
	}

	@Override
	public void moveTurtleTo(double x, double y) {
		turtleController.moveTurtleTo(x, y);
	}

	@Override
	public void drawLine(double x0, double y0, double x1, double y1) {
		turtleController.drawLine(x0, y0, x1, y1);
	}

	@Override
	public void setTurtleAngle(double angle) {
		turtleController.setTurtleAngle(angle);
	}

	@Override
	public void clearScreen() {
		turtleController.clearScreen();
	}

	@Override
	public void showError(String error) {
		if(scriptTab){
			scriptController.showError(error);
		}
		else{
			shellController.showError(error);
		}
	}

	@Override
	public void showText(String text) {
		if(scriptTab){
			scriptController.showText(text);
		}
		else{
			shellController.showText(text);
		}
	}

	private void display() {
		root = variablesController.display(root);
	}

}
