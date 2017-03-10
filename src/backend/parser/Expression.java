package backend.parser;

import java.util.ArrayList;
import java.util.List;
import backend.BackendController;
import backend.Variable;

public abstract class Expression implements java.io.Serializable {
	private static final long serialVersionUID = -1577736662474712482L;
	private Expression parent;
	private Expression breakPointParent;
	private List<Expression> children;
	private int numChildren;
	private transient BackendController controller;
	private Input info;
	private String value = "";

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

	/*
	 * public void setNumChildren(int numChildren) { this.numChildren =
	 * numChildren; }
	 * 
	 * public int getNumChildren() { return numChildren; }
	 */

	public abstract Variable evaluate();

	public Input getInfo() {
		return info;
	}
	public void setInfo(Input info){
		this.info = new Input(info.getInput(), info.getIndex(), info.getBreakPoints());
		this.value = info.get();
	}
	
	public String getString(){
		return value;
	}

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
	}

	public int getNumChildren() {
		return numChildren;
	}
	public void setNumChildren(int numChildren){
		this.numChildren = numChildren;
	}

	public void addChildren(List<Expression> children) {
		this.children.addAll(children);
	}
}
