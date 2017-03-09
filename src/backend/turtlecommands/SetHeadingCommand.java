package backend.turtlecommands;

import java.util.List;

import backend.BackendController;
import backend.Variable;
import backend.parser.Input;
import backend.turtle.TurtleModel;

public class SetHeadingCommand extends TurtleCommand {

	public SetHeadingCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}

	/**
	 * rotate turtle by all arguments, return final angle.
	 */
	@Override
	public double execute() {
		List<TurtleModel> activeTurtles = getTurtlePool().getActiveTurtles();
		for (Variable var : getArgs()) {
			activeTurtles.stream().forEach(e -> e.setHeadingAction(var.getValue()));
		}
		return activeTurtles.get(activeTurtles.size() - 1).getAngleTurned();
	}
}
