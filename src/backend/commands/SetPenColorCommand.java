package backend.commands;

import backend.BackendController;
import backend.Variable;
import backend.parser.Input;

/**
 * @author nikita Set the color of the pen. Supports unlimited parameters
 */
public class SetPenColorCommand extends SimpleCommand {

	public SetPenColorCommand(Input info, BackendController controller) {
		super(info, controller, 1);
	}

	/**
	 * set the pen color to the index indicated by result
	 * 
	 * @param result
	 *            the index to be set
	 * @param curArg
	 *            the next argument, not needed for this example
	 * @return the index that was set
	 */
	@Override
	public double run(double result, Variable curArg) {
		getBackendController().getFrontEndController().getDisplayController().setPenColor((int) result);
		return result;
	}
}
