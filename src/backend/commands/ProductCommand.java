package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.commands.abstracts.SimpleFoldCommand;
import backend.parser.Input;

/**
 * @author nikita This is the implementation of the Product command. An instance
 *         of this class gets created when the parser identifies that the user
 *         typed a Product command
 */
public class ProductCommand extends SimpleFoldCommand {

	public ProductCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	@Override
	public double run(double result, Variable curArg) {
		return result * curArg.getValue();
	}
}
