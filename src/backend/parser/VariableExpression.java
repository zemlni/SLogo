package backend.parser;

import backend.BackendController;
import backend.Variable;
import backend.VariableException;
import backend.commands.MakeVariableCommand;

public class VariableExpression extends Expression {

	public VariableExpression(Input info, BackendController controller) {
		super(info, controller, 0);

	}

	@Override
	public Variable evaluate() {
		try {
			return getBackendController().getParser().getVariableTable().getVariable(getString().substring(1));
		} catch (VariableException e) {
			if (getParent() instanceof MakeVariableCommand) {
				Variable var = new Variable(getString().substring(1), 0);
				getBackendController().getParser().getVariableTable().setVariable(var);
				return var;
			} else {
				getBackendController().getParser().complain(e);
				return null;
			}
		}
	}

}
