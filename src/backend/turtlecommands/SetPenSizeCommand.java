package backend.turtlecommands;

import backend.BackendController;
import backend.Variable;
import backend.commands.SimpleCommand;
import backend.parser.Input;

/**
 * @author nikita set the size of the pen. Supports unlimited parameters
 */
public class SetPenSizeCommand extends SimpleCommand {

	public SetPenSizeCommand(Input info, BackendController controller) {
		super(info, controller, 1);
	}

	/**
	 * set the size of the pen to index.
	 * 
	 * @param index
	 *            the index to be set
	 * @param curArg
	 *            the next arg, not relevant for this method
	 * @return the index that was set
	 */
	@Override
	public double run(double index, Variable curArg) {
		getBackendController().getFrontEndController().getDisplayController().setPenSize((int) index);
		return index;
	}
}
