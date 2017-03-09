package backend.turtlecommands;

import java.util.List;

import backend.BackendController;
import backend.parser.Input;
import backend.turtle.TurtleModel;

public class HomeCommand extends TurtleCommand{

	public HomeCommand(Input in, BackendController controller) {
		super(in, controller, 0);
	}

	@Override
	public double execute() {
		List<TurtleModel> turtleList = getTurtlePool().getActiveTurtles();
		turtleList.forEach(e -> e.homeAction());
		return turtleList.get(turtleList.size() - 1).getDistanceTraveled();
	}
	
}
