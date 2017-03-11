package frontend.variables;

import java.util.Map;

import backend.Variable;
import frontend.IViewConfig;

public class VariablesConfig implements IViewConfig {

	private static final long serialVersionUID = -5478784284629238074L;

	private Map<String, Variable> variables;
	
	public VariablesConfig(Map<String, Variable> variables) {
		this.variables = variables;
	}
	
	public Map<String, Variable> getVariables() {
		return variables;
	}
}
