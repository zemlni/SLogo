package backend.turtlecommands;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

/**
 * @author nikita Set the color of the pen.
 */
public class SetPenColorCommand extends Command {

	public SetPenColorCommand(Input info, BackendController controller) {
		super(info, controller, 1);
	}

	/**
	 * Set the color of the pen. Supports unlimited parameters
	 * 
	 * @return the value of the last color set
	 */
	@Override
	public double execute() {
		double ret = 0;
		for (Variable var : getArgs()) {
			ret = var.getValue();
			getBackendController().getFrontEndController().getDisplayController().setPenColor((int) ret);
		}
		return ret;
	}
}
