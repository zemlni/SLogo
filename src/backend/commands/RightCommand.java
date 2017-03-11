package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Variable;
import backend.parser.Input;
import backend.turtle.TurtleModel;

public class RightCommand extends TurtleCommand {
	public RightCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}

	@Override
	public double execute() {
		double deltaDir = 0;
		List<TurtleModel> turtles = getTurtlePool().getCommandableTurtleModels();
		getTurtlePool().getFrontController().startEventGrouping();
		for (TurtleModel t : turtles) {
			getTurtlePool().setCurrentActiveTurtle(t.getTurtleIDNumber());
			for (Variable var : getArgs()) {
				deltaDir = var.getValue();
				t.rightAction(deltaDir);
			}
		}
		getTurtlePool().getFrontController().endEventGrouping();
		return deltaDir;
	}
}
