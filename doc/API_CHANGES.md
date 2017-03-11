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
addTurtle(int id);
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

4. Added API for debugging:  
```
debug(String input, List<Integer> breakpoints);
step();
```

5. Allow display preference to be changed:  
Added: 
```
DisplayController frontEndController::getDisplayController();
displayController::showColorPalette();
displayController::setPalette(int[] params);
displayController::setPenSize(int width);
displayController::setShape(int index);
displayController::setBackground(int index);
displayController::setPenColor(int index);
displayController::getPenColor();
displayController::getShape();
```

#### Internal API for Front-End

##### public abstract class LocationTransformer 

This is a new class that has been added to allow for different strategies in turtle screen movement. For example different implementations of it could mean the screen is infinite, unbounded, etc. 

1. Constructor: public LocationTransformer(double xOffset, double yOffset)
2. Translate Points to understandable for JavaFX canvas (meaning 0,0 is in top left corner): public Point2D translateLoc(double x, double y)
3. Finds turtle adjusted Location based on new location: public abstract void findTurtleLoc(Point2D location) 
4. Draws lines: public abstract void drawLines(Point2D start, Point2D end, GraphicsContext gc)
5. Sets bounds for when screen shift size: public void setBounds(double xBoundary, double yBoundary)
6. Gets turtle current location: public Point2D getTurtleLoc()

##### TurtleScreenController: 

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


##### public class TurtleImage
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

##### public class VariableEntry extends Button
Class which visually shows a variable entry. Used by the VariableController. Necessary if you wish to extend the basic visual display of a variable. New class for this API. 
1. Constructor: public VariableEntry(Variable variable)
2. Gets variable name: public String getName()
3. Changes value visually: public void changeValue(String value)
4. Changes value visually: public void changeValue(double value)
5. Overwriting of equals method in order use the method remove: public boolean equals(Object o) 

##### public class CommandEntry extends Button 
Class which visually shows a command entry. Used by the CommandsController. Necessary if you
wish to extend the basic visual display of a command.
1. Constructor: public CommandEntry(UserCommand command)
2. Gets Command Name: public String getName()
3. Visually updates command: public void updateCommand(UserCommand command)

##### public class Palette 
Class which is flexibly designed to be a basis for palettes of different varieties. Needed to show different colors assigned to indices. Could be useful for extension. 
1. Constructor: public Palette(List<PaletteEntry> entries)
2. Shows the palette: public void show(){
3. Hides the palette: public void hide(){
4. Adds to the palette / replaces old index: public void add(PaletteEntry entry)

##### public class PaletteEntry extends VBox 
Flexible class which is designed to allow for a wide range of JavaFX components to be made into a suitable entry into the Palette class mentioned above. Kept it general by accepting a node as a parameter.
1. Constructor: public PaletteEntry(Node node, int index){
2. Index associated with node value: public int getIndex(){

##### public class PreferencesWindow 
Basis for building a selection of preferences for the user. The important method in this class is addTab which lets you add to the pre-existing tabs of preferences, ideal for extensions. 
1. constructor: public PreferencesWindow(TurtleScreenController turtleScreenControl){
2. Add tab to window: public void addTab(Tab tab)

##### public abstract class PreferenceTab extends Tab
This class is useful for being a building block for a preferences tab to put in the preferences window.
1. Constructor: public PreferenceTab(TurtleScreenController controller, String titleKey);
2. Implemented in a way specific to each tab: public abstract void addButtons();	
3. Used to allow subclasses to access controller: public TurtleScreenController getController();


## Backend External API Changes:
```
class BackendController{
	boolean evaluate(String command, List<Integer> breakPoints)
	void setLanguage(String language) 
	boolean evaluateFromCurrentBreakPoint()
	boolean evaluateStep()
}
```
The signature of the `evaluate` method was changed. This was changed to accommodate implementing the debugger functionality.  The return type of `evaluate` was also changed in order to tell the front end whether the entered commands finished executing or were stopped at a breakpoint. The `setLanguage` method was added upon learning that internationalization was a requirement for this project. The `evaluateFromCurrentBreakPoint` method was added to enable evaluation once the program was stopped at a breakpoint. This method is called from the front end once a user wants to continue execution. The method `evaluateStep` was added to enable stepping of the program line by line. This is also called from the front end.
```
class Variable{
	void update(double newValue)
	double getValue() 
}
```
Here, the signature of the `update` method was changed. While designing the project, we thought that variables could be strings and ints and doubles. However, once we started developing and learned more about the SLogo language specification, we realized that variables were only doubles. The `getValue` method was updated also because of this reason. 

## Backend Internal API Changes
```
class BackendController{
	TurtleModel getTurtleModel() 
	TreeParser getParser() 
	String getLanguage() 
	FrontEndController getFrontEndController() 
	void setVariable(Variable var) 
	void setBreakPointExpression(Expression expression)
	void setCurrentLine(int lineNumber)
	int getCurrentLine() 
}
```
The `getTurtleModel`, `getLanguage`, `getParser`, `getFrontEndController` and `setVariable` methods were added to the internal API. The first four methods were required by our commands in order to function. These are all part of the internal API and are only called from within the backend. The `setVariable` method was added to remove duplicated code, as there were many instances in which it was required to set a new variable in the `VariableTable` from inside of a command. The `setBreakPointExpression` method was added to the internal backend API in order to enable executing from a breakpoint once one has been reached.  The methods `setCurrentLine` and `getCurrentLine` were added in order to enable execution line by line. These are also only called by commands in the backend.

```
class Command extends Expression{
	Variable evaluate() 
	double execute()
	int getNumArgs() 
	void setNumArgs(int numArgs) 
	void setArgs(List<Variable> vars) 
	List<Variable> getArgs() 
}
```
The method `execute` was changed from being abstract to concrete because the class was changed to being concrete. This was done in connection to redesigning our parser to work in a tree structure. Instances of `Command` are created when a string is identified as a command but was not matched to a language command and was not defined as a user command yet. The method `evaluate` was added because `Command` was made to inherit from `Expression`, as a part of our new `TreeParser`. The methods `getNumArgs`, `setNumArgs`, `setArgs` and `getArgs` were added in order to make all of the concrete commands work that inherit from `Command`. These were needed in order to execute the commands.

```
class Expression{
	abstract Variable evaluate()
	Input getInfo() 
	void setInfo(Input info)
	String getString()
	void addChild(Expression expr) 
	void setParent(Expression expr) 
	Expression getParent() 
	List<Expression> getChildren() 
	BackendController getBackendController() 
	int getNumChildren() 
	void setNumChildren(int numChildren)
	void addChildren(List<Expression> children)
	int getLineNumber()
	void setLineNumber(int lineNumber)
	void setCurrentLine(int lineNumber)
}
```

This class was added in relation to the TreeParser effort. All elements of the syntax are an `Expression` in the tree. `evaluate` is the method that all elements of syntax extending `Expression` have to implement. `getInfo`, `setInfo`, `getString`, `addChild`, `setParent`, `getParent`, `getNumChildren`, `setNumChildren` and `addChildren` were added in order to facilitate the proper creation of Expressions inside of the `TreeParser` class.  `getChildren`, `getBackendController` were added in order to enable the execution of commands, which inherit from this class. Classes that extend this were created for every element of syntax: `ListStartExpression`, `GroupStartExpression`, `VariableExpression`, `Command`, `ConstantExpression` and `BreakPointExpression`. `getLineNumber` and `setLineNumber` were added in the effort make the debugger. `setCurrentLine` was also added in order to make the debugger work. This sets the current line in the frontend on an update in the expressions.
```
class Input {
	void setExpression(Expression expr) 
	Expression getExpression() 
	int getIndex() 
	void incrementIndex() 
	void decrementIndex() 
	String[] getInput() 
	int getLength() 
	String get() 
	List<Integer> getBreakPoints() 
	void incrementCount()
	void decrementByCount()
	Expression getPrevious()
}
```
This class was added in order to simplify the parsing. It represents the input and tracks various important parameters that get accessed throughout the parsing and creation of the tree. This greatly decreased the clutter of `TreeParser` and provided for much cleaner code.
```
public class TreeParser { 
	String getCommandSymbol(String text) throws CommandException 
	String getSyntaxSymbol(String text) throws VariableException
	VariableTable getVariableTable() 
	CommandTable getCommandTable() 
	void complain(Exception e) 
	Expression parse(String input, List<Integer> breakPoints) 
}
```
This class was created to replace the original `Parser` class that was planned in the backend. It maintains the same functionality as before. The methods `getCommandSymbol` and `getSyntaxSymbol` were made public in order to let some types of `Expressions` access these for specific initialization cases, such as a list or a group. The methods `getVariableTable` and `getCommandTable` were added naturally so that the Commands could have access to what was defined in the environment in order to ensure proper execution. The signature of the method `parse` was changed to allow breakpoints. 
```
class Variable{ 
	void update(double newValue) 
	double getValue() 
}
```
The `update` method's signature was changed as discussed in the external API changes above. The method `getValue` was changed for a similar reason. 
```
class CommandTable{
	//removed
	void removeCommand(String name)
	void setCommands(Map<String, Command> commands)
    Map<String, Command> getCommands()
}
```
The method `removeCommand` was removed from the API as this was never used and not specified in the language specification. `setCommands` and `getCommands` were added in order to enable saving and loading the state of the IDE.
```
class VariableTable{
    void setVariables(Map<String, Variable> variables)
    Map<String, Variable> getVariables()
}
```
The methods `getVariables` and `setVariables` were added in order to enable saving and loading the state of the IDE.
