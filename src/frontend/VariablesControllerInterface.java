package frontend;

/**
	 * This interface sets forth the methods that must be implemented in the VariablesController
	 * class. The goal of the VariablesController class is to handle the front-end needs for
	 * showing variables in the variable environment window for the user in the SLogo IDE.
	 * @author Matthew Tribby
	 */
public interface VariablesControllerInterface {
	/**
	 * Adds a variable to be visually shown for the user in the Variables window.
	 * @param variable
	 */
	void addVariable(Variable variable);
	
	/**
	 * Updates the value of a variable that is currently being shown in the Variables window.
	 * @param updatedVariable
	 */
	void updateVariable(Variable updatedVariable);
	
	/**
	 * Removes a variable that is currently being shown in the Variables window.
	 * @param variable
	 */
	void removeVariable(Variable variable);

}
