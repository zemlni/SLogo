package frontend;

	/**
	 * This interface is used to set forth the methods that need to be implemented for the
	 * Script Controller aspect of the front-end. The ScriptController is in charge of the 
	 * Script tab where the user can write scripts of SLogo commands that can be executed.
	 * @author Matthew Tribby
	 */
public interface ScriptControllerInterface {
	/**
	 * Shows an error to the user in the script window for a variety of possible reasons
	 * @param error String representation of error
	 */
	void showError(String error);
	
	/**
	 * Shows helpful or explanatory text to the user
	 * @param text
	 */
	void showText(String text);
	
	/**
	 * Loads a previously saved script
	 * @param filename Script name to be loaded
	 * @throws Exception if script does not exist in saved state with current name. Exception
	 * will be more specifically defined in the future.
	 */
	void load(String filename) throws Exception;
	
	/**
	 * Saves the current script so it can be accessed at a later point
	 * @param filename The name to save the current script as
	 */
	void saveAs(String filename);
}
