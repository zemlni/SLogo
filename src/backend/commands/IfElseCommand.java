package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.parser.Expression;
import backend.parser.Input;

/**
 * @author nikita This is the implementation of the IfElse command. An instance
 *         of this class gets created when the parser identifies that the user
 *         typed a IfElse command
 */
public class IfElseCommand extends Command {

	public IfElseCommand(Input in, BackendController controller) {
		super(in, controller, 3);
	}

	/**
	 * executes the command in the second argument if the first argument
	 * evaluates to nonzero. else exacutes the commands in the third argument.
	 * 
	 * @return the value of the last command executed or 0 if no commands were
	 *         executed
	 */
	@Override
	public double execute() {
		List<Expression> children = getChildren();
		double ret = 0;
		if (children.get(0).evaluate().getValue() != 0)
			ret = children.get(1).evaluate().getValue();
		else
			ret = children.get(2).evaluate().getValue();
		return ret;
	}
}
