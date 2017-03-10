package backend.turtlecommands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;
import backend.turtle.TurtlePool;

public abstract class TurtleCommand extends Command {
	/*
	 * TurtleModel holds reference to FrontEndController. Use
	 * TurtleModel.getFrontController call FrontEnd turtle methods
	 */
	private TurtlePool turtlePool;

	public TurtleCommand(Input in, BackendController controller, int i) {
		super(in, controller, i);
		turtlePool = controller.getTurtlePool();
	}

	public TurtlePool getTurtlePool() {
		return turtlePool;
	}
	
}
