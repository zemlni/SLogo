package backend.turtlecommands;

import java.util.List;

import backend.BackendController;
import backend.parser.Input;
import backend.turtle.TurtleModel;

public class ShowTurtleCommand extends TurtleCommand{
	public ShowTurtleCommand(Input in, BackendController controller) {
		super(in, controller, 0);
	}

	@Override
	public double execute() {
		List<TurtleModel> turtleList = getTurtlePool().getActiveTurtles();
		turtleList.forEach(e -> e.showAction());
		return 1;
	}

}
