SLogo Team08 Plan
==========

Introduction:
-----------
The primary purpose of this project is to create an IDE for the SLogo language. The primary design goals include clear external APIs, and clear internal APIs that are properly organized, written, and separated by their purposes and functions. The program is divided into two overarching components: the frontend, and the backend. The frontend is in charge of handling all of the displays, as well as directly handling all of the user interactions with the program. The backend complements the front end by handling the core computational and executional aspects of the program, as described by the user inputs given to it by the frontend. <br/>
As the nature of this program’s design contains two separate components–front and backend–each component must have an external API, an API that makes contact with the other “end” of the program, as well as an internal API, which handles tasks that solely belong to each individual end. The external API for the frontend creates points of contact that allow the UI to update according to changes and executions in the backend. The external API for the backend provides points of entry that allow the frontend to give the backend commands to execute, as specified by the user, as well as the ability to stop the backend from executing.

Design Overview:
----------
![](SlogoDesign.jpg)
This graph shows our overall design. The program is separated into two parts, the frontend and the backend. The frontend directly controls what the users see and interact with the users. The backend is responsible for understanding the user input and remembering all the user defined commands and variables.<br/>
The frontend external APIs are defined inside FrontendController, including methods that the backend calls to update the view. The backend external API is mainly just the evaluate(String input) method in BackendController, which parsers and executes the user input and udpates the frontend accordingly. The frontend internal APIs are methods that connects between FrontendController and all the sub controllers. The backend internal APIs are methods methods that parses the language and update the variable and command environment.<br/>
When a user wants to evaluate a piece of input string in the frontend, the frontendController.evaluate(input) is called, which then calls backendController.evaluate(input) and the backend starts evaluating the input. Evaluating the input is performed in the parser, which generates a Command object that the backend controller can directly execute. When these command objects are executed, the variable table, the command table, and the properties of the turtle are modified accordingly. During the execution process, whenever an update (variable, command, or turtle) needs to be shown in the frontend, the corresponding method defined in the frontend is called.<br/>

User Interface:
-------------
![](SlogoUI.jpg)
There are four main sub-windows that make up the user interface of the IDE: the Drawing Screen, the Console, the variable/function environment and the history/help window. We will allow for these windows to be resized to allow for maximum flexibility for the user.<br/>
1) Drawing Screen: This screen is where the user will see the turtle drawing being displayed. This screen will be updated when a user enters a turtle-based command, allowing the user to effectively draw through commands. <br/>
2) The Console: This window will have two tabs: the Console tab and the Script tab. The Console table will allow the user to enter commands one by one into the window and execute the command when they press enter. The result will be either returned to the console window or will be reflected in the Drawing screen. If there is an error in the command, the appropriate error will be returned. The Script tab allows the user to write a script of commands which can all be executed in series by the user. In the script tab, there will be new, load,save,step, and run (not pictured but definitely will be there) buttons to help the user handles their command scripts.  In both of these tabs there will be a stop button which can stop execution of a user command when clicked. <br/>
3) The Variable/Function Environment: This window will list all of the variables and functions that are currently in the environment. This window will also allow for variables to be edited when clicked and functions to be viewed when clicked upon.
4) The History/Help window will have two tabs: History and Help. The History tab will show the user’s history of commands in the session. If the user clicks on a line in the command history, it will re-execute that command. In the help tab it will allow for help documentation either for general information or for more specific command help pages to appear. <br/>
The content of these four panels will constitute a session in our SLogo program. Now we will have buttons that allow a user to create a new session (shown in the form of a new tab on top), save the current session for later use, load a saved session, get help (general help screen), and change preferences (i.e. different languages).<br/>
Errors will be output through our console window in the IDE directly after the entered command.
Errors output for the user: Invalid command, File Load error, Save error, function not found, variable not found, overflow, infinite loop, invalid parameters (return function format)<br/>

API Details:
----------
**External API for Front-end (Graphical Interface)**
```java
class FrontendController { // (one for each Session)
	// methods to update the session variables and positions
	void addVariable(Variable variable)
	void updateVariable(Variable updatedVariable)
	void removeVariable(Variable variable)
	void addCommand(Command command)
	void removeCommand(Command command)
	// methods to respond to user input
	void moveTurtleTo(double x, double y)
	void drawLine(double x0, double y0, double x1, double y1)
	void setTurtleAngle(double angle)
	void clearScreen()
	// methods for displaying response text
	void showError(String error)
	void showText(String text)
}
```
**Internal API for Front-end**
```java
class FrontendController {
void evaluate(String input)
}
class ShellController {
void showError(String error)
void showText(String text)
}
class ScriptController {
void showError(String error)
void showText(String text)
void load(String filename)
void saveAs(String filename)
}
class VariablesController {
	void addVariable(Variable variable)
	void updateVariable(Variable updatedVariable)
void removeVariable(Variable variable)
}
class CommandsController {
	void addCommand(Command command)
	void removeCommand(Command command)
}
class HistoryController {
	void addHistory(String history)
	void clearHistory()
}
class MainController {
	void newSession()
void loadSession(String filename)
void saveSessionAs(String filename)
}
class TurtleController {
	void moveTurtleTo(double x, double y)
	void drawLine(double x0, double y0, double x1, double y1)
	void setTurtleAngle(double angle)
	void clearScreen()
}
```
**External API for Back-end (Interpreter)**
```java
Class BackendController{
	void evaluate(String command)  // parses the instruction received from the GUI and and evaluates the commands returned by parser one by one, updating UI on each step.
	void stopExecution() // probably involves multithreading?
}

class Variable{
	String getKey();
	update();
}
```
**Internal API for Back-End**
```java
class Parser{
	Public List<Command> parse(String command) throws CommandError //parse the command, check if it exists. Check if variables are defined in variable table. Throws a CommandError if there was an offending command.
}
class variableTable{
	//whenever table changes need to update frontend
	Variable getVariable(String name)
	void setVariable(String name, Object value)
	void removeVariable(String name)
}
class CommandTable{
	Command getCommand(String name)
	void setCommand(Command command)
	void removeCommand(String key)
}
abstract class Command{
	//how to instantiate arguments for commands - tuesday lecture	
	public abstract void execute() //execute command, update variables and commands and update front end accordingly. Check variables if they were defined in the `VariableTable`
}
abstract class UserCommand extends Command{
	//for this execute, check if the command was already defined by the user. 
	public String getKey()
	public void update()
}
```

API Example Code:
------------
*The user types 'fd 50' in the command window, and sees the turtle move in the display window leaving a trail, and the command is added to the environment's history.* <br/>
When the user types, the characters are displayed in the command window. When the user hits enter or run, then the command window calls the evaluate method in FrontendController and pass ‘fd 50’ as input. The evaluate method then calls backendController.evaluate(“fd 50”) to let the backend evaluate the given string. The backend then calls the `parse(String command)` method of `Parser` to try to match the typed command to an existing command. In this case, the command is matched to `ForwardCommand`, and a new instance of that class is created with 50 as its argument. The method `parse` creates a list and adds to it the new command and returns it to `evaluate`. `evaluate` then goes through this list anc calls each command’s `execute()` method, which will execute the command (in this example move the turtle forward 50 units) and update the front end accordingly (change the turtle’s location as needed). When the backend finishes evaluation, the frontend calls historyController.addHistory(input) to add the command to history. <br/><br/>

Use cases:<br/>
1) *A global variable is created by ‘set :x 10’:* the backend is called to evaluate(‘set :x 10’).  After parsing, the backend creates a new `SetCommand` with “x” and 10 as its arguments. The `execute()` method of the newly created command is called, and a new Variable(“x”,”10”) is created. Additionally, it is necessary to add this new `Variable `to its `VariableTable` and calls frontendController.addVariable(variable). The frontendController then calls variablesController.addVariable(variable) to show this global variable in the variable window. <br/>
2) *A wrong command is entered:* the backend figures out that the command is unknown or the parameters are wrong. This occurs when the `parse(String input)` command of `Parser` cannot match the typed command to any pre-defined commands or any user created commands. `parse` throws an error. `evaluate` detects that an error was thrown and then calls frontendController.showError(errorMsg) with the corresponding error message. The frontend then checks whether the call is initiated from a command console or a script window, and display the error message accordingly. <br/> 
3) *The user writes and executes a script:* The user opens the script tab next to the console and writes the script. Next, the user presses the `Run` button. The front-end reads the script in as one long string and sends this string to the `evaluate` method of the class `BackendController`. Next, `evaluate` calls the method `parse` in the `Parser`. Here, each command will be parsed and transformed into an instance of `Command` corresponding to the one the user typed. Commands will be added to a `List<Command>`. This `List<Command>` will be returned to `evaluate`. `evaluate` will execute each command before parsing the next one. To execute the command, the `execute` method of the `Command` will be called on each `Command` in the list to perform the appropriate action and update the front end. Inside `execute`, commands will be responsible for checking that variables are defined in the `VariableTable`. `UserCommands` will also be responsible to check the `CommandTable` during run-time that they were already defined. On each step of `execute` the `VariableTable` and the `CommandTable` may have to be updated depending on which commands were execute. <br/>
4) *The user writes and tries to execute a script with a bug:* The program will proceed the same way as in the previous example. Each command will be parsed and executed up until the faulty command is reached. This can happen in the `parse` method, or in the `execute` method of the `Command` itself. Several things can go wrong here. The command could be non-existing. If the command contains some variables, the variables may not be defined. Additionally, the command might exist but the syntax for the command might be incorrect. If any of these cases are detected, `evaluate` alerts the front-end accordingly. <br/> 
5) *A variable, labeled num:5 is clicked and edited with the value being updated:* 
Upon the click, the JavaFX front-end will be notified because of a click interaction with a node. Then for that node it will open up a box which will allow the user to change the value through keyboard input. Errors will be handled if there is a certain type the variable has to be and the user enters invalid input. Then now that we have the Variable instance that we need to update, we will call `Variable.update(Object input)` which will change the value in the instance. Then to change the value in the variableTable, `variableTable.setVariable(String variableName, Object value)` will need to be called, presumably from within that update method. After this is done the entire back-end is updated. Then `FrontEndController.updateVariable(Variable updatedVariable)` will be called which will then call `VariableController.updateVariable(Variable updatedVariable)` which will change the front-end visualization of the variable table.
<br/>
6) *A command, fd 100, is clicked on in the history tab, which calls the command:* 
Upon the click, the JavaFX front-end will be notified because of a click interaction with that node (Text) in the history. Then for that node it will get the text that is contained in that node and pass it to the back-end using the `evaluate(String text)` method from the FrontEndController. From there, execution will proceed like any other command. This calls `BackEndController.evaluate(String text)`. The BackEndController then calls `Parser.parse(String command)` which will try to match the command with a type of Command. Then a match will be found with the BackCommand class and a new instance of BackCommand is created with 100 being passed in the constructor. This new Command is part of the List<Command> which is returned by Parser back to the `BackEndController.evaluate(String text)` method. Then this method will go through the list (in this case of length 1) and call the `execute()` method of the command. This `execute()` method will call `FrontEndController.moveTurtleTo(new x position, new y position)` which will then call `TurtleController.moveTurtleTo(new x position, new y position)`. If the pen is down then additionally, `FrontEndController.drawLine(old x, old y, new x, new y)` will be called which will then call `TurtleController.drawLine(old x, old y, new x, new y)` which will draw the line. When the back-end finishes evaluation, the front-end calls `historyController.addHistory(input)` to add the command to history.
<br/>
7) *The user writes and executes an iterative script:* The program proceeds in the same way as in the previous example of executing a script. When the parser reaches an iterative script, the parser will set the variables and List<Commands> within the iterative command object. The variables will be set according to the number of iterations specified in the iterative command’s arguments. The List<Commands> within the iterative command will be populated with the nested commands. Execution proceeds as per usual, with the extra nuance that the iterative command will call it’s internal execute until it has completed its designated number of executions, before the program proceeds to execute non nested commands. <br/>
8) *The user writes and executes an iterative or recursive script that runs infinitely:* The program proceeds in the same way as in the previous example, but instead, the iterative command gets “stuck” or runs infinitely. If the user determines that the script he has run is taking too long to execute, the user will press a stop button the user interface that calls the stopExecution() method in the external API for the backend. This method kills the backend execution process, and throws an error, notifying the user that the execution did not complete and that the user had chosen to stop the execution of the commands. <br/>

Design Considerations:
-------------
While planning SLogo, our team discussed several main design decisions extensively. These were as follows.<br/>
1) *How to update the front-end on back-end changes:* There were two options for updating the front-end once the back-end changed. The first was to pass back some kind of `Response` object from the back-end to the front-end that contained the response generated from the back-end. This response could contain either directions related to drawing the turtle or a textual response to be printed to the console. The advantage of this option was that there would be a uniform way of passing the response, and the front-end would simply check for a response each time the user entered a new command. This would be simple and organized in the implementation - the `Response` class would be subclassed for each different responses (`TextResponse`, `TurtleResponse`, and possibly other new ones). These would be passed to the front-end and the front-end would unpackage them and display the response accordingly. This would also be organizationally beneficial in that the front end would never be called from the backend from multiple places. However, the tradeoff is that when a command would generate a large result, this whole result would be given back at once and would have to be stored somewhere. This would be problematic if the result were very big. Because of this, we chose to go with the second option. The other option considered by our group was to have a the front-end be told to update by the back end when the backend produced updates. Whenever a single command finished executing, it would tell the front end to update. This would be done on a per-command basis. Namely, commands that have to do with the turtle will tell the front-end to draw, while commands that do not have to do with the turtle will output text to the console. This way, storing a very long result of a command would not have to occur, as it would be immediately passed to the front-end. For example, under this model, we would not have to store the results of all iterations of a for loop, but can just output them to the front-end if needed on each iteration. This also takes away the need for any kind of `Response` hierarchy, and does not limit the flexibility of the design. Because of these reasons, we chose the second option. This reasoning also applies to updating the variable and function windows of the UI. These will only be updated when needed, and not on every single command. This is done for the same reasons described above. <br/>
2) *Where to store the history:* There were also two options of where to handle the history of commands in the IDE. The first option, the more intuitive one, was to store the history in the backend, and access it from the front end. Initially, our group was going to use this design as it seemed the clearest. The advantage of this design is that the front-end would not have to hold any information, it would just handle displaying it and fetching it from the back-end. However, our group realized that the back-end did not need access to the history at all, and that passing it back and forth between the front-end and the back-end would have been extra lines of code that could have been avoided. In this design, the command history will be saved immediately when the user enters the command, and will be maintained in the front-end code. If needed to execute again, the back-end will be fed the selected command the same way that the back-end usually receives a command. The drawback of this design, of course, is that there has to be a history structure in the front-end. However, this design was chosen because it will provide for cleaner and more concise and maintainable code. <br/>
3) *Turtle code:* Our group discussed the details of where to store the turtle’s location in the back-end. Since the turtle functionality is a core piece of the project, we first decided that there would be a special class that handles the properties of a turtle. Inside this class, there would be methods to change the turtle’s location, change its angle and other turtle-related utilities. These methods would have been called by the `Command` instances that relate to the turtle. However, on thinking about the problem more, we realized that the position and orientation of the turtle are nothing more than regular variables in the system, just like ones defined by the user with the command `MAKE` or `SET`. This realization led us to a different design. We will keep those two variables in the back-end in the `VariableTable` along with all of the user-defined variables. This way, we will eliminate the presence of a special `Turtle` class. Moreover, this design enables us to treat turtle commands in a more uniform way instead of treating them as special cases. This makes our design more extensible and flexible. If we will be required to add new commands to the turtle, we will just subclass from `Command` accordingly and will not have to add any additional functionality in a `Turtle` class which we would have had to do otherwise. Additionally, this would have caused code duplication between the front-end and the back-end. For these reasons, we chose the second design. <br/>
4) *Scripts:* Our group discussed how to handle scripts - whether to send the whole script as one long string to the back-end, or whether to give it line by line. The first design was chosen in this case. We decided that splitting lines was the job of the back-end and the that front-end should not have anything to do with that kind of work. This was an organizational decision. The advantage of this is more flexibility with our code: if we need to change the way we parse, we will only have to change code in one place, not both in the back-end and in the front-end. Additionally, a point of discussion was whether to give the whole script to the parser at once and have the `parse` method decompose this into different commands, or whether to decompose the script into separate lines in the `evaluate` method and feed each line to `parse` and have `parse` only parse one command at a time. The first method was chosen because with the second one, it would have been harder to deal with for - loops and such. The drawback of this design is that the `Command` instances will have to be responsible for retrieving their variables from the `VariableTable`. Additionally, user-defined commands may not be able to be checked at the parser level. We will have to create the command with the given arguments and name and when time comes to call it, we will check in the `CommandTable` if the command was defined earlier in the script by the user. Because of this, we chose to use the design where the `parse` method returns a `List<Command>` and the commands will be responsible for checking the variables and user defined commands will have to be verified at run-time.

Team Responsibilities:
--------------
The team responsibilities will be split up as follows: Keping and Matt will work on the front-end, and Gabriel and Nikita will work on the back-end. We will each be responsible for testing, debugging, and refactoring our own code. These are our primary responsibilities. Next, we will all participate in merging and resolving conflicts. These are our secondary responsibilities.

As for the specific division of front-end team: Keping and Matt will write up the external APIs together, and then Keping will be responsible for the user input window, history window, and the application entrance class, while Matt will be responsible for the variables and commands windows, and the turtle window.