package frontend;

/**
 * Interface for the front end controller. The role of this controller is to oversee
 * and direct all of the objects that make up the front-end of the SLogo program.
 * @author Matthew Tribby
 */
public interface FrontEndControllerInterface {
	
		/**
		 * External API: method which passes the string input on the command line / current
		 * script line to the back-end to be processed
		 * @param input
		 */
		void evaluate(String input);
		
		// methods to update the session variables and positions
	
		/**
		 * Internal: Will ensure that a variable is added visually to the Variable window
		 * @param variable the Variable instance to be added
		 */
		void addVariable(Variable variable);
		
		/**
		 * Internal: Updates a variable that already is shown in the Variable window
		 * @param updatedVariable
		 */
		void updateVariable(Variable updatedVariable);
		
		/**
		 * Internal: Removes the visual representation of a Variable that is currently shown 
		 * in the Variable window
		 * @param variable
		 */
		void removeVariable(Variable variable);
		
		/**
		 * Internal: Adds a command to the Commands Controller which keep tracks of Commands on the
		 * front-end
		 * @param command
		 */
		void addCommand(Command command);
		
		/**
		 * Internal: Removes the command from the Commands Controller which keeps tracks of Commands on
		 * the front-end
		 * @param command
		 */
		void removeCommand(Command command);
		
		// methods to respond to user input
		/**
		 * Internal: Moves the turtle (or visual point of pen) to a certain specified location
		 * @param x new x-coordinate
		 * @param y new y-coordinate
		 */
		void moveTurtleTo(double x, double y);
		
		/**
		 * Internal: Draws a line which is useful for tracking the turtle's/pen's movement
		 * @param x0 starting x
		 * @param y0 starting y
		 * @param x1 ending x
		 * @param y1 ending y
		 */
		void drawLine(double x0, double y0, double x1, double y1);
		
		/**
		 * Internal: Sets the new angle of the turtle/pen. This determines how the turtle/pen will move
		 * around the screen.
		 * @param angle Angle specified in degrees
		 */
		void setTurtleAngle(double angle);
		
		/**
		 * Internal: Clears the drawing screen, resets the turtle back to initial position and gets
		 * rid of all drawn lines
		 */
		void clearScreen();
		
		// methods for displaying response text
		
		/**
		 * Internal: Displays an error that has occurred during the processing of a certain command/function
		 * @param error String representation of error
		 */
		void showError(String error);
		
		/**
		 * Internal: Displays any text that the user may need to see
		 * @param text
		 */
		void showText(String text);
}
