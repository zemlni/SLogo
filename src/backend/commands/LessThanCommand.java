package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

/**
 * @author nikita This is the implementation of the LessThan command. An
 *         instance of this class gets created when the parser identifies that
 *         the user typed a LessThan command
 */
public class LessThanCommand extends Command {

	public LessThanCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}
	
	/** return 1 if the first argument is less than the other. else return 1
	 * 
	 * @return 1 if the first argument is less than the other. else return 1
	 * */
	@Override
	public double execute() {
		return getArgs().get(0).getValue() < getArgs().get(1).getValue() ? 1 : 0;
	}
}
