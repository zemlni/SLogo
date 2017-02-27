package backend;

import java.util.HashMap;

import frontend.app.FrontEndController;

public class VariableTable implements VariableTableInterface {

	private HashMap<String, Variable> variables;
	private FrontEndController frontEndController;

	public VariableTable(FrontEndController frontEndController) {
		variables = new HashMap<String, Variable>();
		this.frontEndController = frontEndController;
	}

	@Override
	public Variable getVariable(String name) throws CommandException {
		Variable ret = variables.get(name.toUpperCase());
		if (ret == null)
			throw new CommandException(name);
		return ret;
	}

	@Override
	public void setVariable(Variable var) {
		variables.remove(var.getKey().toUpperCase());
		variables.put(var.getKey().toUpperCase(), var);
		frontEndController.addVariable(var);
	}

	@Override
	public void removeVariable(Variable var) {
		variables.remove(var.getKey().toUpperCase());
		try {
			frontEndController.removeVariable(var);
		} catch (Exception e) {
			// TODO: make this query a resources file. talk to keping.
			frontEndController.showError("Variable requested was not found!");
		}
	}

}
