## SLogo IDE

Our IDE for SLogo let users run (and debug?) SLogo programs interactively. Users are shown various states of the workspace, and are allowed to change some preferences.

#### User Input View - Run and Debug:

There are two tabs for user input, one is a script window, the other is a shell window. 

Inside script view, the user runs the input by clicking the "run" button. There are buttons for the user to open a text file and load it to the script window, and to save the current script to a file. The user can also enter debug mode by clicking the "debug" button, and specifying the line numbers by entering space/newline separated numbers in the thin left text area. In debug mode, the program stops executing on the breakpoint, and then when "step" button is clicked, it steps one line.

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

#### 
