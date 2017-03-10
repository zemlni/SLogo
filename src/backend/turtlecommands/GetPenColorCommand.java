package backend.turtlecommands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

public class GetPenColorCommand extends Command {

	public GetPenColorCommand(Input info, BackendController controller) {
		super(info, controller, 0);
	}
	@Override
	public double execute(){
		return getBackendController().getFrontEndController().getDisplayController().getPenColor();
	}
}
