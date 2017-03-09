package backend.turtlecommands;

import java.util.List;

import backend.BackendController;
import backend.Variable;
import backend.parser.Input;
import backend.turtle.TurtleModel;

public class SetTowardsCommand extends TurtleCommand {

	public SetTowardsCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	/**
	 * sets the bearing of the turtle towards all coordinates specified by the
	 * arguments, one after the other
	 */
	@Override
	public double execute() {
		List<Variable> args = getArgs();
		List<TurtleModel> activeTurtles = getTurtlePool().getActiveTurtles();
		for (int i = 0; i < args.size(); i += 2) {
			double towX = getArgs().get(i).getValue();
			double towY = getArgs().get(i + 1).getValue();
			activeTurtles.stream().forEach(e -> e.setTowardsAction(towX, towY));
		}
		return activeTurtles.get(activeTurtles.size() - 1).getAngleTurned();
	}

}
