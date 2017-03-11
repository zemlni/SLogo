package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

/**
 * @author nikita Get the index of the shape of the turtle.
 */
public class GetShapeCommand extends Command {

	public GetShapeCommand(Input info, BackendController controller) {
		super(info, controller, 0);
	}

	/**
	 * get the index of the current shape of the turtle
	 * 
	 * @return the index of the current shape of the turtle.
	 */
	@Override
	public double execute() {
		return getBackendController().getFrontEndController().getDisplayController().getShape();
	}

}
