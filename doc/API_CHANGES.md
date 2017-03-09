### API Changes

#### External APIs between front-end and back-end

1. turtle commands:  
Original:
```
moveTurtleT(double x, double y);
drawLine(double x0, double y0, double x1, double y1);
setAngle(double angle);
showTurtle();
hideTurtle();
```
Changed:
```
moveTurtle(int id, double x0, double y0, double x1, double y1, boolean penDown);
rotateTurtle(int id, double startAngle, double endAngle);
showTurtle(int id);
hideTurtle(int id);
```
Reasons for the change:  
  a. We need both start position/angle to allow animation.  
  b. We need an id to allow multiple turtles.  
  
2. text commands:  
Original:
```
showError(String errorMsg);
```
Changed:
```
showError(String errorMsg, String bad);
appendText(String text);
```
The changes are made to allow more flexibility of text display, and using of resource files to display error prompt in different languages.

3. apis for commands in animation:  
Added:
```
switchToQueueMode();
switchToInstantMode();
startEventGrouping();
endEventGrouping();
```
These are to allow for different priorities of the commands passed from backend, and for grouped events that shall be executed simultaneously.

####Internal API for Front-End

#####public class LocationTransformer 
This is a new class that has been added to allow for different strategies in turtle screen movement. For example different implementations of it could mean the screen is infinite, unbounded, etc. 
1. Constructor: public LocationTransformer(double xOffset, double yOffset)
2. Translate Points to understandable for JavaFX: public Point translateLoc(double x, double y)
3. Finds turtle adjusted Location based on new location: public void findTurtleLoc(Point location) 
4. Draws lines: public void drawLines(Point start, Point end, GraphicsContext gc)
5. Sets bounds for when screen shift size: public void setBounds(double xBoundary, double yBoundary)
6. Gets turtle current location: public Point getTurtleLoc()

#####TurtleScreenController: 
formerly titled Turtle Controller. Changed name to more accurately reflect that this controller was for handling the screen where turtle moves.
1. Sets instance variable: public void setFrontEndController(FrontEndController frontEndController)
2. Adds new turtle to the screen: Needed for multiple turtles: public void addTurtle(int idNumber)
3. Sets Pen Color, needed for advanced features of screen: public void setPenColor(Color penColor)
4. Changes the thickness of the pen, needed for advanced features of the screen: public void changePenThickness(double newWidth)
5. Changes the color of the background, needed for advanced features of the screen: public void setBackground(Color color)
6. Added an integer id to differentiate between different turtles: public void moveTurtleTo(int id, double x, double y) 
7. Added an integer id to differentiate between different turtles: public void setTurtleAngle(int id, double angle) 
8. Method needed to hide or show a turtle, left out of the original API: public void showTurtle(int id)
9. Method needed to hide a turtle, left out of the original API: public void hideTurtle(int id)


######public class TurtleImage { 
This is a new class which is used to represent a single turtle. Originally just had a single Turtle Controller without necessarily a way to implement the turtle. TurtleImage contains visual components that make a turtle on the turtle screen.
1. Constructor: public TurtleImage(int idNumber, LocationTransformer locationTransformer, double startingX, double startingY, FrontEndController frontEnd)
2. Second Constructor with initial spot of (0,0): public TurtleImage(int idNumber, LocationTransformer locationTransformer, FrontEndController frontEnd)
3. Left out of original API, needed for user flexibility: Changes image of the turtle: public void setImage(Image newImage)
4. Continuation of call from the TurtleScreenController.setTurtleAngle() from the original API: public void setAngle(double angle)
5. Needed to visually display the turtle on the screen: public ImageView getImage() 
6. This method was included in original API but now in different class: public void moveTo(double x, double y)
7. Visually shows turtle on screen: left out of original API: public void show()
8. Visually hides turtle on screen: left out of original API: public void hide()
9. Gets the circle which visually shows if turtle is selected or not, Needed for advanced features: public Circle getCircle() 
10. Updates whether the turtle is selected or not, Needed for advanced features: public void updateCurrent() 

#####public class VariableEntry extends Button
Class which visually shows a variable entry. Used by the VariableController. Necessary if you wish to extend the basic visual display of a variable. New class for this API. 
1. Constructor: public VariableEntry(Variable variable)
2. Gets variable name: public String getName()
3. Changes value visually: public void changeValue(String value)
4. Changes value visually: public void changeValue(double value)
5. Overwriting of equals method in order use the method remove: public boolean equals(Object o) 

#####public class CommandEntry extends Button 
Class which visually shows a command entry. Used by the CommandsController. Necessary if you
wish to extend the basic visual display of a command.
1. Constructor: public CommandEntry(UserCommand command)
2. Gets Command Name: public String getName()
3. Visually updates command: public void updateCommand(UserCommand command)
 

