package frontend;

import backend.Variable;

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
	 * @throws Exception if variable that is trying to be updated does not currently
	 * exist in the front-end. This exception will be more specifically defined.
	 */
	void updateVariable(Variable updatedVariable) throws Exception;
	
	/**
	 * Removes a variable that is currently being shown in the Variables window.
	 * @param variable
	 * @throws Exception if variable that is trying to be removed does not currently
	 * exist in the front-end. This exception will be more specifically defined.
	 */
	void removeVariable(Variable variable) throws Exception;

}
