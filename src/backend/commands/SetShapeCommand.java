package backend.commands;

import backend.BackendController;
import backend.Variable;
import backend.parser.Input;

/**
 * @author nikita set the shape of the turtle. Supports unlimited parameters.
 */
public class SetShapeCommand extends SimpleCommand {

	public SetShapeCommand(Input info, BackendController controller) {
		super(info, controller, 1);
	}

	/**
	 * set the shape to the current index
	 * 
	 * @param result
	 *            the index to be set
	 * @param curArg
	 *            the next arg, not relevant for this method
	 * @return the index that was set
	 */
	@Override
	public double run(double result, Variable curArg) {
		getBackendController().getFrontEndController().getDisplayController().setShape((int) result);
		return result;
	}

}
