package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.parser.Expression;
import backend.parser.Input;

public class IfCommand extends Command {

	public IfCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	@Override
	public double execute() {
		List<Expression> children = getChildren();
		double ret = 0;
		if (children.get(0).evaluate().getValue() != 0)
			ret = children.get(1).evaluate().getValue();
		return ret;
	}

}
