package backend.commands;

import backend.BackendController;
import backend.commands.abstracts.SimpleMapCommand;
import backend.parser.Input;

/**
 * @author nikita Set the color of the pen. Supports unlimited parameters
 */
public class SetPenColorCommand extends SimpleMapCommand {

	public SetPenColorCommand(Input info, BackendController controller) {
		super(info, controller, 1);
	}

	@Override
	public double run(double argValue) {
		getBackendController().getFrontEndController().getDisplayController().setPenColor((int) argValue);
		return argValue;
	}
}
