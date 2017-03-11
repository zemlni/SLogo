package frontend.frontend.animation;

import backend.Variable;
import frontend.variables.VariablesController;

public class RemoveVariableEvent extends VariablesViewEvent {

	private Variable variable;
	
	public RemoveVariableEvent(VariablesController control, Variable variable) {
		super(control);
		this.variable = variable;
	}

	@Override
	public double update(double dt) {
		try {
			// TODO: Hey! Don't throw this to me!
			control.removeVariable(variable);
		} catch (Exception e) {
			setFinishedTrue();
		}
		setFinishedTrue();
		return dt;
	}

}
