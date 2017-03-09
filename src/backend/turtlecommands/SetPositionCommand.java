package backend.turtlecommands;

import java.util.List;

import backend.BackendController;
import backend.Variable;
import backend.parser.Input;
import backend.turtle.TurtleModel;

public class SetPositionCommand extends TurtleCommand {

	public SetPositionCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	/**
	 * moves turtle to all coordinates specified by arguments, one after the
	 * other.
	 */
	@Override
	public double execute() {
		List<Variable> args = getArgs();
		List<TurtleModel> activeTurtles = getTurtlePool().getActiveTurtles();

		double oldX = 0;
		double oldY = 0;
		double newX = 0;
		double newY = 0;
		for (int i = 0; i < args.size(); i += 2) {
			newX = getArgs().get(i).getValue();
			newY = getArgs().get(i + 1).getValue();
			activeTurtles.stream().forEach(e -> e.setPositionAction(newX, newY));
		}	 	
		return activeTurtles.get(activeTurtles.size()).getDistanceTraveled();
	}
}
