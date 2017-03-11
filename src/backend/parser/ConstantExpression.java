package backend.parser;

import backend.BackendController;
import backend.Variable;

/**
 * @author nikita This is the implementation for the Constant expression. It is
 *         created when a constant is encountered by the parser.
 */
public class ConstantExpression extends Expression {

	public ConstantExpression(Input info, BackendController controller) {
		super(info, controller, 0);
	}

	/**
	 * Returns a new variable with the value of the parsed constant when called
	 * to be evaluated
	 * 
	 * @return a new variable with the value of the constant
	 */
	@Override
	public Variable evaluate() {
		Variable ret = null;
		if (checkLines()){
			ret = new Variable(null, Double.parseDouble(getString()));
			setCurrentLine(getParent().getLineNumber());
		}
		return ret;
	}

}
