package backend.parser;

import backend.BackendController;
import backend.Variable;

public class ConstantExpression extends Expression {

	public ConstantExpression(Input info, BackendController controller) {
		super(info, controller, 0);
	}

	@Override
	public Variable evaluate() {
		return new Variable(null, Double.parseDouble(getString()));
	}

}
