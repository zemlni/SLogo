package frontend.nonfxml.config;

import java.util.List;

import backend.Variable;
import frontend.nonfxml.IViewConfig;

public class VariablesConfig implements IViewConfig {

	private static final long serialVersionUID = -5478784284629238074L;
	private List<Variable> variables;
	
	public VariablesConfig(List<Variable> savedVariables) {
		this.variables = savedVariables;
	}
	public List<Variable> getVariables() {
		return variables;
	}
}
