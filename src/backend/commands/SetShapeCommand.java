package backend.commands;

import backend.BackendController;
import backend.commands.abstracts.SimpleMapCommand;
import backend.parser.Input;

/**
 * @author nikita set the shape of the turtle. Supports unlimited parameters.
 */
public class SetShapeCommand extends SimpleMapCommand {

	public SetShapeCommand(Input info, BackendController controller) {
		super(info, controller, 1);
	}

	@Override
	public double run(double argValue) {
		getBackendController().getFrontEndController().getDisplayController().setShape((int) argValue);
		return argValue;
	}

}
