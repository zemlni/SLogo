package backend.parser;

import backend.BackendController;
import backend.Variable;

/**
 * @author nikita This class represents the expression that is created when a
 *         breakpoint is declared in the code. It only has one child and points
 *         to the command at the beginning of that line.
 */
public class BreakPointExpression extends Expression {

	public BreakPointExpression(Input info, BackendController controller) {
		super(info, controller, 1);
		info.set(info.get().substring(1));
		info.decrementIndex();
	}

	/**
	 * returns 0. Does not go on to evaluate children. This sets the current
	 * breakpoint in BackendController to the child of this command.
	 */
	@Override
	public Variable evaluate() {
		getBackendController().setBreakPointExpression(this.getChildren().get(0));
		return null;
	}
}
