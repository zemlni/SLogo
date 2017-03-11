package backend;

import java.util.HashMap;
import java.util.Map;
import frontend.frontend.FrontEndController;

/**
 * @author nikita This class is the implementation of the Variable table. It is
 *         created and maintained inside the parser. It has methods for
 *         updating, getting and setting variables that are defined by the user
 *         or the users functions. The front end is updated accordingly.
 */
public class VariableTable implements VariableTableInterface {

	private Map<String, Variable> variables;
	private FrontEndController frontEndController;

	public VariableTable(FrontEndController frontEndController) {
		variables = new HashMap<String, Variable>();
		this.frontEndController = frontEndController;
	}

	/**
	 * try to get a variable with the name name
	 * 
	 * @param name
	 *            the name of the variable requested
	 * @returns the variable requested
	 * @throws VariableException
	 *             if the variable requested is not in the variable table
	 */
	@Override
	public Variable getVariable(String name) throws VariableException {
		Variable ret = variables.get(name.toUpperCase());
		if (ret == null)
			throw new VariableException(name);
		return ret;
	}

	/**
	 * set the variable var in the variable table
	 * 
	 * @param var
	 *            the variable to be added to the variable table
	 */
	@Override
	public void setVariable(Variable var) {
		variables.remove(var.getKey().toUpperCase());
		variables.put(var.getKey().toUpperCase(), var);
		frontEndController.addVariable(var);
	}

	/**
	 * remove the variable from this variable table and from the front end. This
	 * happens when loops or user defined functions terminate and has to do with
	 * variable scope.
	 * 
	 * @param var
	 *            the variable to be removed
	 */
	@Override
	public void removeVariable(Variable var) {
		variables.remove(var.getKey().toUpperCase());
		try {
			frontEndController.removeVariable(var);
		} catch (Exception e) {
			frontEndController.showError("VariableError", var.getKey());
		}
	}
	
	public boolean contains(String name) {
		return variables.keySet().contains(name.toUpperCase());
	}

	public Map<String, Variable> getVariables(){
		return variables;
	}
	
	public void setVariables(Map<String, Variable> variables){
		this.variables = variables;
		frontEndController.clearVariables();
		for (String key: variables.keySet())
			frontEndController.addVariable(variables.get(key));
	}

}
