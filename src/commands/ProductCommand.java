package commands;

import backend.BackendController;
import backend.Command;

public class ProductCommand extends Command {

	public ProductCommand(BackendController controller) {
		super(controller);
		setNumArgs(2);
	}

	@Override
	public double execute() {
		return getArgs().get(0).getValue() * getArgs().get(1).getValue();

	}

}
