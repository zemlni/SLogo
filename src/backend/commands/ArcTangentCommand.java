package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

/**
 * @author nikita This class is the implementation of the ArcTangent command. An
 *         instance of this class gets created when the parser identifies that
 *         the user has entered an ArcTangent command.
 */
public class ArcTangentCommand extends Command {

	public ArcTangentCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}

	/**
	 * evaluate the arctan of the argument
	 * 
	 * @return the arctan of the argument
	 */
	@Override
	public double execute() {
		return Math.atan(getArgs().get(0).getValue());
	}
}