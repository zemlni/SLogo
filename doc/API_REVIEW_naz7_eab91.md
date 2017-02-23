API Review naz7 eab91
=====
Nikita Zemlevskiy naz7 
I will be talking about the backend portion of the project. 

## Part 1

### What about your API/design is intended to be flexible?
We designed our API to be flexible with respect to adding new commands. This can be seen in our inheritance hierarchy for commands. It is easy to add a command by just adding a new class that inherits from the `Command` superclass.  Additionally, we designed scripts to behave exactly like commands. Because of this, there was no need to write extra code to represent scripts. This provides flexibility if additional functionality or plugins were to be written for the IDE on the frontend side. 

### How is your API/design encapsulating your implementation decisions?
The API for commands has one main method, `execute`, which returns the return value of the executed command. This encapsulates all of the functionality of specific commands, making it therefore unnecessary for an outside user to know how a command works. This is the main encapsulation element of the backend. Additionally, Additionally, functionality of the `Parser` is encapsulated, in that the outside user is only able to call `parse`. This is useful because the outside caller does not have to worry about any string processing, and just hand the entered string to the user.

### What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
One exception that occurs in the backend happens in the `Parser` class in the method `parse`. This occurs when a command entered is not matched to any existing or user defined commands. These exceptions are thrown and caught (1) when the command is parsed and (2) when a command instance is attempted to be created from the string parsed in. These errors are handled inside the `parse` method. They are caught and the front end is updated accordingly, displaying to the user the cause of the error (the offending string entered). This happens with variables as well. 

### Why do you think your API/design is good (also define what your measure of good is)?
I believe that my API design is good because it is flexible and extensible. The main task of the backend is to handle many different commands, which suggests that it should be easy to add new kinds of commands in the future. This has been handled in our design. Next, it is also possible to add functionality to the parser or create a new kind of parser that behaves differently. All one would need to do to make this is implement the methods in the `ParserInterface`. This enforces all of the functionality required for a proper parser. 

## Part 2
### How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
The factory design pattern is represented in the design of the `Parser`. It handles creating the different kinds of `Command` instances based on what is entered by the user. Additionally, The design uses the chain of responsibility design pattern. This is seen when the front end portion of the project calls the `evaluate` method, which in turn calls the `parse` method to parse the entered code, which in turn creates commands and tells them to `execute`, which in turn calls other private methods which carry out `Command` specific functionality. 
### How do you think the "advanced" Java features will help you implement your design?
Reflection is used to create different instances of `Command` objects, determined by the string entered by the user. Regular expressions are also used to parse the command entered by the user, in order to create those commands using reflection. Generics may also be helpful for reducing duplicated code in the `Commands` inheritance hierarchy.
### What feature/design problem are you most excited to work on?
I am most excited to work on the `Parser` and `Command` hierarchy. This is an interesting feature and also an interesting problem from the design side, because it will be tricky to set up all of the commands and have them check the `VariableTable` and `CommandTable`. 
### What feature/design problem are you most worried about working on?
I am worried about the amount of work to be done in the `Command` hierarchy. Additionally, figuring out user defined commands will be hard, because we chose to represent them as regular commands. This will be interesting but hard to figure out. 
### Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams)
1. *The user defines a new command* The user will define a new command in the frontend. This will be passed into the backend. The backend will parse the `TO` command and create a new `ToCommand` instance. The arguments will be parsed and added to the command. Next, the command will `execute`. This will create the new command as the user requested and add it to the `CommandTable`. If the command is created successfully, 0 will be returned and printed to the user. Otherwise, 1 will be returned to the user on the console. 
2. *The user enters a non existing command* The `Parser` will try to parse the command. It will not be found, and it will not exist in the user defined `CommandTable` either. An exception will be thrown and `parse` will catch it and update the front end with the offending command.
3. *The user enters fd 50* The command will be created as usual by the method `parse` in the class `Parser`. Then, the command will be executed and 50 will be returned to the front end to be printed on the console. 
4. *The user updates a variable from the UI* The variable that the user clicked on will be notified of the update along with the new value. This will call the `update` method in the corresponding `Variable` object, and will set the new value of the variable, and update it in the `VariableTable`.
5. *The user executes a script from the UI* The script will be passed to the `evaluate` method. The script will be then fed to `parse` and will be evaluated command by command the usual way. The final value will be returned to the user. 

eab91