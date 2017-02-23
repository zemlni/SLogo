#API Review
mrt28 and jrc63 worked on this file. This file contains the analysis for slogo_team08

#Part One
#####1. What about your API is designed to be flexible?
The fact that there is one main FrontEndController that is in charge of all the sub-controllers. This allows for easy extensibility and clean code because that means there is only one place that the back-end has to interact with. The use of controllers is commendable because it allows for more abstraction and better encapsulation of the actual details because the BackEndController doesn't have to worry about any subclasses of the front-end. The Command class is also defined to be flexible because its general, abstract class allows for many diverse and different implementations of it with the same basic API.  

#####2. How is your API encapsulating your implementation decisions?
Command parsing is completely hidden from the front-end. Additionally, the implementation of the actual Commands is hidden from the front-end because it never has to deal with the Command. Additionally, the VariableTable implementation and CommandsTable implementation is also hidden, leaving an on-looker with no information on what data structure that table is. The Back-end also has no information on the subclasses of the front-end as it only interacts with the FrontEndController.

#####3. What Exceptions?
The main exceptions to worry about with the front end are if the user inputs an invalid command name or a command with invalid parameters (either wrong type or no parameters). These are handled in the back-end and then the back-end will use the external API of the front-end to call FrontEndController.showError(String error) to actually show that error on the screen. This way of showing errors to the user is universal for all errors which makes it very adaptable and flexible for our design. 

#####4. Why is the design good and what makes good design?
My team's design is good because it consolidates the front-end's control into a single class which makes it very easy for the back-end to interact with it. Additionally, the abstraction of Commands makes it very easy to implement a wide-range of commands that can be as complex or simple as we want. The overall solid level of abstraction leads to less confusion, more flexibility, and no worry of confusion when extending. A good API should be defined as having logical, easy to use methods while also trying to limit the number of methods while still maximizing the productivity and usability of these methods. A good API should also limit the number of points of contact and interaction between the front-end and back-end to ensure that there is no unnecessary confusion.

#Part Two
#####1.
One example of a design pattern that we are both utilizing in our designs is the Command pattern. The basis of this pattern is that there is an over-arching abstract Command class with concrete sub-classes which encapsulate the actual implementation of these commands and give forth only a simple API, consisting of one command, execute() which is responsible for carrying out all commands, regardless of their complexity. One design pattern that I should implement into my design is the Observer design pattern because it allows for less confusion and complexities in interacting between classes. It will helpful for limiting what needs to be passed between the front-end and the back-end and will make the overall program more generic and less lambda dependent. The idea behind Observer is that when object changes its state, that all its dependents are notified and then call use that information to update their own state. This will be very helpful in this program because updates in the back-end can easily be mirrored in the front-end.

#####2.
Binding will play an important part in implementing the Observer design pattern which is discussed in the previous question. Binding will limit the number of lambda functions that we need to implement and will allow for a design that is closer to the traditional MVC structure. It will also limit the points of necessary interaction with the front-end and back-end because it will allow an automatic way of interaction that does not need to be hard-coded. Reflection will also be super important as it will allow us to easily implement the Command pattern listed above. Because in our design we are passing in the user input as a String, the back-end can simply find the function name within that string and use the capabilities of Reflection to make the proper sub-class of the Command.

#####3. 
I am most interested in implementing the turtles for this program and seeing how their interaction across front-end and back-end is going to change over our process. I think it will be a challenge to properly pass and visualize the more complex turtle commands, especially handling certain cases like when the turtle's movement sends them off the drawing window. I think it is an exciting challenge and will need to involve everyone in the team to fit together a design solution to both encapsulate the turtle while trying to make the front-end and back-end as generic as possible for possible extension.

#####4.
I am most worried about the actual assembling of the GUI from its component parts. Most notably, I am worried about implementing the most complex GUI I have ever built while still trying to make it as generic as possible by not hard-coding details in and allowing for future flexibility. I am also worried about how we will change the window dimensions and how we will make it so that these dynamic changes affect the other windows (for example, if the drawing window is shrunk, we would want to make sure that the turtle's possible grid of being shown is shrunk in that sub-section). I think this will be a great challenge.

#####5. 
1. User enter "adsf 27". This should throw an error </br> 
How this would work is that when the user sends this command, it would go to the FrontEndController which would call evaluate(String text) which then calls the BackEndController.evaluate(String text). Now this would pass the command to the Parser class which then should be able to identify that "adsf" is not a proper, existing command in the group of commands. Then the back-end would identify the proper command response and use the FrontEndController.showError(errorMessage). This would then pass to the corresponding panel and print out the error message.
</br>
2. Choosing a new turtle image </br>
The user will be able to press a button which will open a file chooser which will allow the user to choose from JPEGs. Of course, there will be type checking to ensure that the file entered actually is a JPEG/supported image type and is not some other type of file. Then the image will be switched in the internal definition of the TurtleController class. This probably will require a new, previously undefined public method setTurtleImage() that will need to be added to the API.
</br>
3. Change pen color </br>
The user will be able to look inside the preferences tab at the top of the GUI and then be able to select something like "change pen color". This would open up a display box which lets the user choose from a selected range of colors (This may be through RGB values like a traditional color wheel or only by letting them choose a certain subset through names). Then after the color is chosen it will have to be passed to the TurtleController which will have to set the pen color (this will have to be a new API method)
</br>
4. Changing the language of the command </br>
This will also be inside the preferences tab at the top of the GUI. There will be an option to "Choose Language". Then there will be a basic drop-down menu which will allow the user to choose from a pre-defined list of languages. You would have to notify the back-end of this change (would need to add this method to the external API). After choosing one and exiting out, the user will then be able 
</br>
5. Pre-formatted HTML help pop-up </br>
This will be accessed through a help button which placed at the top of the GUI. When the user clicks on this button it will open up the HTML file rendered in a new window which the user will be able to control and is separate from the main GUI where the program is being run. This does not affect the program state whatsoever. 

