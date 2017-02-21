package slogo_team08;

/**
 * This is the interface for the structure that will hold the variable table.
 * This will be accessed from the commands in the backend and will update the
 * variable window in the front end. This will contain all user defined
 * variables. This is only internal API.
 */
public interface VariableTable {

	/**
	 * get a variable by its name
	 * 
	 * @param name
	 *            the name of the variable requested
	 * 
	 * @return the Variable requested
	 */
	public Variable getVariable(String name);

	/**
	 * Set a new or existing variable to the given value
	 * 
	 * @param name
	 *            name of variable
	 * @param value
	 *            value to be set
	 */
	public void setVariable(String name, Object value);

	/**
	 * remove a variable from the variable table
	 * 
	 * @param name
	 *            name of variable to be removed
	 */
	public void removeVariable(String name);
}
