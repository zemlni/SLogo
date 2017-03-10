package backend;

import java.util.List;
import backend.parser.Expression;
import backend.parser.TreeParser;
import backend.turtle.TurtlePool;
import frontend.app.FrontEndController;

/**
 * @author nikita This class is the main backend controller. It has an instance
 *         of turtle controller, the parser and other relevant instance
 *         variables. It is created with English as the default language. An
 *         instance of this class is created and maintained by the frontend
 *         controller.
 */
public class BackendController implements BackendControllerInterface {

	private TreeParser parser;
	private String language;
	private TurtlePool turtlePool;
	private FrontEndController fcontroller;
	private Expression breakPointExpression;
	private boolean byLine;
	private int currentLine;

	public BackendController(FrontEndController frontEndController) {
		this.fcontroller = frontEndController;
		setLanguage("English");
		turtlePool = new TurtlePool(frontEndController);
	}
	
	public TurtlePool getTurtlePool() {
		return turtlePool;
	}
	
	public void toggleTurtle(int id){
		
	}
	
	public void setPenDown(int id){
		
	}
	
	public void setPenUp(int id){
		
	}

	/**
	 * parse the command given by the string and set the break points given in
	 * the list. Then evaluate the commands, stopping at the break points if any
	 * are provided
	 * 
	 * @param command
	 *            the command to be evaluated
	 * @param breakPoints
	 *            list of breakpoints to be set
	 * @return true if the command finished evaluating, else false (if stopped
	 *         at breakpoint)
	 */
	@Override
	public boolean evaluate(String command, List<Integer> breakPoints) {
		byLine = false;
		Expression root = parser.parse(command, breakPoints);
		return evaluateFromExpression(root);
	}

	/**
	 * Set the language. reinitializes the parser because commands are defined
	 * differently. This also reinitializes the variable table and the command
	 * table
	 */
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

	public void setBreakPointExpression(Expression expression) {
		this.breakPointExpression = expression;
	}

	/**
	 * Continue the evaluation of the commands already entered, when stopped at
	 * the breakpoint
	 * 
	 * @return true if the commmads finished executing, else false
	 */
	public boolean evaluateFromCurrentBreakPoint() {
		return evaluateFromExpression(breakPointExpression);
	}

	private boolean evaluateFromExpression(Expression expr) {
		try {
			Variable eval = expr.evaluate();
			double ret = eval.getValue();
			fcontroller.showText(String.valueOf(ret));
			return true;
		} catch (Exception e) {
			//TODO: notify frontend of new current line.
			return false;
		}
	}

	public boolean getByLine() {
		return byLine;
	}

	public int getCurrentLine() {
		return currentLine;
	}

	/**
	 * step the program and notify front end of current line
	 * 
	 * @return whether the program has completed execution or not
	 */
	public boolean evaluateStep() {
		byLine = true;
		return evaluateFromCurrentBreakPoint();
	}

	public void setCurrentLine(int lineNumber) {
		this.currentLine = lineNumber;
	}
}
