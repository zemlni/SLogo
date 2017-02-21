package frontend;
	
	/**
	 * This interface sets forth the methods that must be implmeneted in the HistoryController
	 * class. The goal of the HistoryController class is to handle the front-end needs of the
	 * History window which displays the history of commands the user has entered and allows
	 * the user to click on these commands to execute them.
	 * @author Matthew Tribby
	 */
public interface HistoryControllerInterface {
	
	/**
	 * Adds history to the history window, visually displaying the recent commands
	 * @param history Command to be added
	 */
	void addHistory(String history);
	
	/**
	 * Clears the history window
	 */
	void clearHistory();
}
