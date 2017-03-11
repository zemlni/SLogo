package backend.commands;

import backend.BackendController;
import backend.commands.abstracts.SimpleMapCommand;
import backend.parser.Input;
/**@author nikita Set the background. Supports unlimited parameters.
 * */
public class SetBackgroundCommand extends SimpleMapCommand {

	public SetBackgroundCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}

	@Override
	public double run(double argValue) {
		getBackendController().getFrontEndController().getDisplayController().setBackground((int)argValue);
		return argValue;
	}
}
