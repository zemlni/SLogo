package backend.turtlecommands;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

/**
 * @author nikita set the shape of the turtle
 */
public class SetShapeCommand extends Command {

	public SetShapeCommand(Input info, BackendController controller) {
		super(info, controller, 1);
	}

	/**
	 * set the shape of the turtle. Supports unlimited parameters.
	 * 
	 * @return the index of the last set shape.
	 */
	@Override
	public double execute() {
		double ret = 0;
		for (Variable var : getArgs()) {
			ret = var.getValue();
			getBackendController().getFrontEndController().getDisplayController().setShape((int) ret);
		}
		return ret;
	}

}
