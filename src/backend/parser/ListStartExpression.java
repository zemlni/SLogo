package backend.parser;

import java.util.List;
import backend.BackendController;
import backend.Variable;

/**
 * @author nikita This class is the implementation of the List expression. An
 *         instance of this class gets created when the parser identifies that a
 *         list has been typed by the user.
 */
public class ListStartExpression extends Expression {

	public ListStartExpression(Input info, BackendController controller) {
		super(info, controller);
		String arg = "";
		if (info != null) {
			try {
				int numBrackets = 0;
				while (numBrackets >= 0) {
					info.incrementIndex();
					info.incrementCount();
					String curArg = info.get();
					arg += curArg + " ";
					String symbol = getBackendController().getParser().getSyntaxSymbol(curArg);
					if (symbol.equals("ListEnd"))
						numBrackets--;
					else if (symbol.equals("ListStart"))
						numBrackets++;
				}
				arg = arg.substring(0, arg.length() - 2);
			} catch (Exception e) {
				getBackendController().getParser().complain(e);
			}
			if (arg.length() > 0) {
				this.addChildren(getBackendController().getParser().parse(arg, info.getBreakPoints()).getChildren());
				for (Expression expr : getChildren())
					expr.setParent(this);
			}
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
