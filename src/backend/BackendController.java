package backend;

import java.util.ArrayList;
import java.util.List;

import backend.parser.Expression;
import backend.parser.TreeParser;
import frontend.app.FrontEndController;

public class BackendController implements BackendControllerInterface {

	private TreeParser parser;
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

		List<Integer> breakPoints = new ArrayList<Integer>();

		Expression root = parser.parse(command, breakPoints);
		Variable eval = root.evaluate();
		double ret = eval.getValue();
		fcontroller.showText(String.valueOf(ret));
	}

	@Override
	public void setLanguage(String language) {
		if (!language.equals(this.language)) {
			this.language = language;
			parser = new TreeParser(this);
		}
	}

	public TreeParser getParser() {
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
