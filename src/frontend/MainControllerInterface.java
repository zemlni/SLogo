package frontend;

	/**
	 * This interface sets forth the methods that must be implemented in the MainController
	 * class. The goal of this class is to be in charge of a session which represents one
	 * workspace in the IDE. A session is comprised of the states of the drawing window,
	 * variables/functions window, command/script window, and the history/help window. This
	 * interface adds functionality to control these sessions.
	 * @author Matthew Tribby
	 */
public interface MainControllerInterface {
	
	/**
	 * Creates a new session for the user with windows with default state
	 */
	void newSession();
	
	/**
	 * Loads a previously saved session and sets the windows' state to their saved state.
	 * @param filename Name of Session to be opened
	 */
	void loadSession(String filename);
	
	/**
	 * Saves the current session
	 * @param filename Name to be saved under
	 */
	void saveSessionAs(String filename);
}
