package backend.parser;

import java.util.List;
import backend.BackendController;
import backend.Variable;

/**
 * @author nikita This class is the implementation of the List expression. An
 *         instance of this class gets created when the parser identifies that a
 *         list has been typed by the user.
 */
public class ListStartExpression extends MultiExpression {

	public ListStartExpression(Input info, BackendController controller) {
		super(info, controller, "List");
		if (getArg().length() > 0) {
			this.addChildren(getBackendController().getParser().parse(getArg(), info.getBreakPoints()).getChildren());
			for (Expression expr : getChildren())
				expr.setParent(this);
		}
	}

	public ListStartExpression(BackendController controller) {
		this(null, controller);
	}

	/**
	 * evaluate all of the children of this list.
	 * 
	 * @return the value of the last executed command
	 */
	@Override
	public Variable evaluate() {
		if (checkLines()) {
			List<Expression> children = getChildren();
			Variable ret = new Variable(null, 0);
			for (Expression child : children) {
				ret = child.evaluate();
			}
			return ret;
		} else
			return null;
	}
}
