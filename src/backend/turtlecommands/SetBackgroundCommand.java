package backend.turtlecommands;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

public class SetBackgroundCommand extends Command {

	public SetBackgroundCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}
	/**Set the background. Supports unlimited Parameters
	 * 
	 * @return the value of the last argument
	 * */
	@Override
	public double execute(){
		double ret = 0;
		for (Variable var: getArgs()){
			ret = var.getValue();
			getBackendController().getFrontEndController().getDisplayController().setBackground((int)ret);
		}
		return ret;
	}
}
