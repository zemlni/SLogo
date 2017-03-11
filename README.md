## SLogo IDE

#### Team 8: Gabriel, Keping, Matt, and Nikita

Our IDE for SLogo let users run (and debug?) SLogo programs interactively. Users are shown various states of the workspace, and are allowed to change some preferences.

We started from Feb 17, and ended at March 9. Different members put different time and overall it should be 40 hours for each person on average.

Keping and Matt work on the front-end, and Gabriel and Nikita work on the backend. 

The file to start the project is `main.Main`.

#### User Input View - Run and Debug:

There are two tabs for user input, one is a script window, the other is a shell window. 

Inside script view, the user runs the input by clicking the "run" button. There are buttons for the user to open a text file and load it to the script window, and to save the current script to a file. 

The user can also enter debug mode by clicking the "debug" button, and specifying the line numbers by entering space/newline separated numbers in the thin left text area. In debug mode, the program stops executing on the breakpoint. Then when "step" button is clicked, it steps one line.

Inside shell view, the user runs the input by pressing enter. Return value from the command will be shown in the shell.

#### Variables View, Commands View, and History View:

User defined variables and commands are shown respectively in the variables view and commands view. History view stores all the user input. The views are collapsible and adjustable.

The user can click the variables displayed in the variables window to change variable values. The user can also click history to put the selected history to the current user input view (shell or script).

#### Turtle Screen View:

The turtle screen shows the turtles and the drawn lines. There is a Preferences button in the turtle screen, which pops up a window to allow the user to choose multiple gui settings: 
1. pen color  
2. pen thickness  
3. pen up or pen down
4. background color
5. turtle image
6. selected turtle highlight
7. turtle moving speed
8. hide or show turtles

There is also a Color Palette button, which when clicked shows the available colors and their indices.

#### Sessions and Menu:

Each session stores an independent workspace, and there are tabs to switch sessions. Users can use the New button on the top menu to create a new session tab, the Open button to open a saved session, the Save button to save the current session.

A saved session stores the information of variables, commands, history, and user preferences of turtle screen outlook. 

The Help button shows a JavaFX browser with the commands website as the default help page.

There is a language button, which allows the user to choose language for both the user interface and the language of the SLogo commands.

#### SLogo Commands:

We have covered all of the [SLogo basic commands](http://www.cs.duke.edu/courses/compsci308/spring17/assign/03_slogo/commands.php) and [SLogo extended commands](http://www.cs.duke.edu/courses/compsci308/spring17/assign/03_slogo/commands2_J2W.php). Specifically, we can allow multiple turtles, unlimited arguments, self recursion, and dynamic variable scopes. 

Commands that may take unlimited arguments are as follows: and, backward, difference, equal, forward, left, notequal, or, product, quotient, remainder, right, setHeading, setPosition, setTowards, sum and make variable. However, a command has to be defined before it is called (even when "called" inside another command body), so mutual recursion is not allowed.

#### Bugs:

1. The debugger is still not fully functional. It only steps one line (cannot step into function body).

#### How the assignment could be improved:

The assignment is well designed.

