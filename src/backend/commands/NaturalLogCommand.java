package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

/**
 * @author nikita This is the implementation of the NaturalLog command. An
 *         instance of this class gets created when the parser identifies that
 *         the user typed a NaturalLog command
 */
public class NaturalLogCommand extends Command {

	public NaturalLogCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}

	/**
	 * evaluate the ln of the argument
	 * 
	 * @return the ln of the argument
	 */
	@Override
	public double execute() {
		return Math.log(getArgs().get(0).getValue());
	}
}