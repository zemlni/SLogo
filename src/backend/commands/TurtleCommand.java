package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;
import backend.turtle.TurtlePool;

public abstract class TurtleCommand extends Command {

	public TurtleCommand(Input in, BackendController controller, int i) {
		super(in, controller, i);
	}

	public TurtlePool getTurtlePool() {
		return getBackendController().getTurtlePool();
	}
	
}
