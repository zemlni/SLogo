package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.TurtleModel;

public abstract class TurtleCommand extends Command {
	/*
	 * TurtleModel holds reference to FrontEndController. Use
	 * TurtleModel.getFrontController call FrontEnd turtle methods
	 */
	private TurtleModel turtle;

	public TurtleCommand(BackendController controller, int i) {
		super(controller, i);
		turtle = controller.getTurtleModel();
	}

	public TurtleModel getTurtle() {
		return turtle;
	}
	
}
