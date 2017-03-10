package backend.turtlecommands;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

/**
 * @author nikita set the size of the pen
 */
public class SetPenSizeCommand extends Command {

	public SetPenSizeCommand(Input info, BackendController controller) {
		super(info, controller, 1);
	}

	/**
	 * set the size of the pen. Supports unlimited parameters.
	 * 
	 * @return the value of the last argument
	 */
	@Override
	public double execute() {
		double ret = 0;
		for (Variable var : getArgs()) {
			ret = var.getValue();
			getBackendController().getFrontEndController().getDisplayController().setPenSize((int) ret);
		}
		return ret;
	}
}
