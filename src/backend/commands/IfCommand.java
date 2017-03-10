package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.parser.Expression;
import backend.parser.Input;

/**
 * @author nikita This is the implementation of the If command. An instance of
 *         this class gets created when the parser identifies that the user
 *         typed an if command
 */
public class IfCommand extends Command {

	public IfCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	/**
	 * execute the commands in the list in the second argument if the first
	 * argument evaluates to a nonzero value.
	 * 
	 * @return the value of the last evaluated command or 0 if no commands were
	 *         executed
	 */
	@Override
	public double execute() {
		List<Expression> children = getChildren();
		double ret = 0;
		if (children.get(0).evaluate().getValue() != 0)
			ret = children.get(1).evaluate().getValue();
		return ret;
	}
}
