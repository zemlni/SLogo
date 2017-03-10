package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

/**
 * @author nikita This is the implementation of the Pi command. An instance of
 *         this class gets created when the parser identifies that the user
 *         typed a Pi command
 */
public class PiCommand extends Command {

	public PiCommand(Input in, BackendController controller) {
		super(in, controller, 0);
	}

	/**
	 * returns the value of Pi.
	 * 
	 * @return the value of Pi.
	 */
	@Override
	public double execute() {
		return Math.PI;
	}
}