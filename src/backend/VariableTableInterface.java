package backend;

/**
 * This is the interface for the structure that will hold the variable table.
 * This will be accessed from the commands in the backend and will update the
 * variable window in the front end. This will contain all user defined
 * variables. This is only internal API.
 */
public interface VariableTableInterface {

	/**
	 * get a variable by its name
	 * 
	 * @param name
	 *            the name of the variable requested
	 * 
	 * @return the Variable requested
	 * @throws VariableException 
	 */
	public Variable getVariable(String name) throws VariableException;

	public void setVariable(Variable var);

	/**
	 * remove a variable from the variable table
	 * 
	 * @param var
	 *            variable to be removed
	 */
	void removeVariable(Variable var);
}
