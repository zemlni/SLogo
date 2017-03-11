package backend.parser;

import java.util.ArrayList;
import java.util.List;
import backend.BackendController;
import backend.Variable;

/**
 * This class is the superclass for all expressions. It contains
 */
public abstract class Expression implements java.io.Serializable {
	private static final long serialVersionUID = -1577736662474712482L;

	private Expression parent;
	private List<Expression> children;
	private int numChildren;
	private transient BackendController controller;
	private Input info;
	private String value = "";
	private int lineNumber;

	public Expression(Input info, BackendController controller, int numChildren) {
		this(info, controller);
		this.numChildren = numChildren;
	}

	public Expression(Input info, BackendController controller) {
		children = new ArrayList<Expression>();
		this.controller = controller;
		if (info != null)
			setInfo(info);
	}

	/**
	 * Check if current execution mode is line by line. If so, return true if
	 * this expression is still on the line of execution. Else return false. If
	 * mode is not line by line, return true
	 * 
	 * @return true if this expression should be evaluated. else false
	 */
	public boolean checkLines() {
		boolean sameLine = lineNumber == controller.getCurrentLine();
		return sameLine || !controller.getByLine();
	}
	
	public void setCurrentLine(int i){
		controller.setCurrentLine(i);
	}

	/**
	 * Evaluate this expression and return a variable containing the result
	 * 
	 * @return the variable containing the result of the evaluation
	 */
	public abstract Variable evaluate();

	public Input getInfo() {
		return info;
	}

	public void setInfo(Input info) {
		this.info = new Input(info.getInput(), info.getIndex(), info.getBreakPoints(), info.getLineNumbers());
		this.value = info.get();
	}

	public String getString() {
		return value;
	}

	/**
	 * Add a child to the expression
	 * 
	 * @param expr
	 *            the child to be added
	 */
	public void addChild(Expression expr) {
		children.add(expr);
	}

	public void setParent(Expression expr) {
		parent = expr;
	}

	public Expression getParent() {
		return parent;
	}

	public List<Expression> getChildren() {
		return children;
	}

	public BackendController getBackendController() {
		return controller;
	}

	// TODO: written by Keping. This is bad code.
	// Only to be used when reloading an expression
	public void setBackendController(BackendController controller) {
		this.controller = controller;
		for (Expression childExpression : children) {
			childExpression.setBackendController(controller);
		}
	}

	public int getNumChildren() {
		return numChildren;
	}

	public void setNumChildren(int numChildren) {
		this.numChildren = numChildren;
	}

	public void addChildren(List<Expression> children) {
		this.children.addAll(children);
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public int getLineNumber() {
		return lineNumber;
	}
}
