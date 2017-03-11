package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

/**
 * @author nikita get the index of the current pen color
 */
public class GetPenColorCommand extends Command {

	public GetPenColorCommand(Input info, BackendController controller) {
		super(info, controller, 0);
	}

	/**
	 * get the current pen color
	 * 
	 * @return the index of the current pen color
	 */
	@Override
	public double execute() {
		return getBackendController().getFrontEndController().getDisplayController().getPenColor();
	}
}
