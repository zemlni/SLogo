package backend;

import javafx.application.Platform;
import javafx.concurrent.Task;

import frontend.app.FrontEndController;

public class BackendController implements BackendControllerInterface {

	private Parser parser;
	private String language;
	private TurtleModel turtle;
	private FrontEndController fcontroller;

	/**
	 * english is default language
	 */
	public BackendController(FrontEndController frontEndController) {
		this.fcontroller = frontEndController;
		setLanguage("English");
		turtle = new TurtleModel(frontEndController);
	}

	public TurtleModel getTurtleModel() {
		return turtle;
	}

	@Override
	public void evaluate(String command) {
		double ret = parser.parse(command);
		fcontroller.showText(String.valueOf(ret));
	}

	@Override
	public void setLanguage(String language) {
		this.language = language;
		parser = new Parser(this);

	}

	public Parser getParser() {
		return parser;
	}

	public String getLanguage() {
		return language;
	}

	public FrontEndController getFrontEndController() {
		return fcontroller;
	}

	public void setVariable(Variable var) {
		parser.getVariableTable().setVariable(var);
	}

}
