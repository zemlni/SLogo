package backend.commands;

import backend.BackendController;
import backend.commands.abstracts.SimpleMapCommand;
import backend.parser.Input;

/**
 * @author nikita set the size of the pen. Supports unlimited parameters
 */
public class SetPenSizeCommand extends SimpleMapCommand {

	public SetPenSizeCommand(Input info, BackendController controller) {
		super(info, controller, 1);
	}

	@Override
	public double run(double argValue) {
		getBackendController().getFrontEndController().getDisplayController().setPenSize((int) argValue);
		return argValue;
	}
}
