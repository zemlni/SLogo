External API for Front-end (Graphical Interface)
onEnter - when the instruction is entered
	calls a parser in the backend
changeConfiguration - in general some sort of ways to tell the back end configurations
	might just pass in a command with the parser

External API for Back-end (Interpreter)
parse - parses the instruction received from the GUI and decides on what command the user is asking for configuration
getStatus- Gets the status of variables and functions to display information on front-end
getHistory - gets history of commands 

