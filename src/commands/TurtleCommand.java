package commands;

import backend.BackendController;
import backend.Command;
import backend.Parser;
import backend.TurtleModel;
import frontend.FrontEndController;

public abstract class TurtleCommand extends Command {
	/*
	 * TurtleModel holds reference to FrontEndController. Use TurtleModel.getFrontController call FrontEnd turtle methods
	 */
	private TurtleModel turtle;
	public TurtleCommand(BackendController controller) {
		super(controller);
		turtle = controller.getTurtleModel();
	}
	
	public TurtleModel getTurtle(){
		return turtle;
	}
}
