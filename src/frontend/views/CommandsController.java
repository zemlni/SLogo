package frontend.views;

import frontend.Command;
import frontend.app.FrontEndController;


/**
 * The goal of this class is to control the Commands window which show the 
 * commands that are saved and can be executed. This commands are basically functions created
 * by the user.
 * @author Matthew Tribby
 */
public class CommandsController {

	private FrontEndController frontEnd;
	public void setFrontEndController(FrontEndController frontEnd) {
		this.frontEnd = frontEnd;
	}
	
	public void addCommand(Command command) {
		// TODO Auto-generated method stub
		
	}

	public void removeCommand(Command command) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
