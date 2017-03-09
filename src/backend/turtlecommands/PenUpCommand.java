package backend.turtlecommands;

import java.util.List;

import backend.BackendController;
import backend.parser.Input;
import backend.turtle.TurtleModel;

public class PenUpCommand extends TurtleCommand{

	public PenUpCommand(Input in, BackendController controller) {
		super(in, controller, 0);
	}

	@Override
	public double execute() {
		List<TurtleModel> turtleList = getTurtlePool().getActiveTurtles();
		turtleList.stream().forEach(e -> e.penUpAction());
		return 0;
	}
}
