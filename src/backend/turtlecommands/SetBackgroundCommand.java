package backend.turtlecommands;

import backend.BackendController;
import backend.Variable;
import backend.commands.SimpleCommand;
import backend.parser.Input;
/**@author nikita Set the background. Supports unlimited parameters.
 * */
public class SetBackgroundCommand extends SimpleCommand {

	public SetBackgroundCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}
	/**
	 * set the background color to the index indicated by result
	 * 
	 * @param result
	 *            the index to be set
	 * @param curArg
	 *            the next argument, not needed for this example
	 * @return the index that was set
	 */
	@Override
	public double run(double result, Variable curArg) {
		getBackendController().getFrontEndController().getDisplayController().setBackground((int)result);
		return result;
	}
}
