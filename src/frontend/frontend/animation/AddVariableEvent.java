package frontend.frontend.animation;

import backend.Variable;
import frontend.variables.VariablesController;

public class AddVariableEvent extends VariablesViewEvent {

	private Variable variable;
	
	public AddVariableEvent(VariablesController control, Variable variable) {
		super(control);
		this.variable = variable;
	}

	@Override
	public double update(double dt) {
		control.addVariable(variable);
		setFinishedTrue();
		return dt;
	}

}
