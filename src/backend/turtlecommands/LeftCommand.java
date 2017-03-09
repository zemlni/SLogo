package backend.turtlecommands;

import java.util.List;

import backend.BackendController;
import backend.Variable;
import backend.parser.Input;
import backend.turtle.TurtleModel;

public class LeftCommand extends TurtleCommand {
	public LeftCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}

	@Override
	public double execute() {
		double deltaDir = 0;
		for (Variable var : getArgs()) {
			deltaDir = var.getValue();
			List<TurtleModel> activeTurtles = getTurtlePool().getActiveTurtles();
			activeTurtles.stream().forEach(e -> e.leftAction(var.getValue()));
		}
		return deltaDir;
	}

}
