package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

/**
 * @author nikita This is the implementation of the Not command. An instance of
 *         this class gets created when the parser identifies that the user
 *         typed a Not command
 */
public class NotCommand extends Command {

	public NotCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}

	/**
	 * return 1 if the argument is 0 and 0 if the argument is nonzero
	 * 
	 * @return return 1 if the argument is 0 and 0 if the argument is nonzero
	 */
	@Override
	public double execute() {
		return getArgs().get(0).getValue() == 0 ? 1 : 0;
	}	
}
