package frontend;
	
	/**
	 * This interface sets forth the methods that must be implemented in the TurtleController
	 * class. This class is used to visually show the turtle's/pen's movement and drawing
	 * in the drawing window.
	 * @author Matthew Tribby
	 */
public interface TurtleControllerInterface {
	/**
	 * Moves the turtle/pen to the specified location, visually updating the JavaFX scene
	 * so that the user can see the turtle's/pen's new location
	 * @param x new x-coordinate with center of the screen being 0
	 * @param y new y-coordinate with center of the screen being 0
	 */
	void moveTurtleTo(double x, double y);
	
	/**
	 * Draws a line which is useful for tracking turtle/pen movement and drawing in the
	 * drawing window based on that movement
	 * @param x0 original x-coordinate with the center of the screen being 0
	 * @param y0 original y-coordinate with the center of the screen being 0
	 * @param x1 ending x-coordinate with the center of the screen being 0
	 * @param y1 ending y-coordinate with the center of the screen being 0
	 */
	void drawLine(double x0, double y0, double x1, double y1);
	
	/**
	 * Sets the angle of the turtle/pen
	 * @param angle Angle in degrees
	 */
	void setTurtleAngle(double angle);
	
	/**
	 * Clears the drawing screen and resets turtle/pen
	 */
	void clearScreen();
}
