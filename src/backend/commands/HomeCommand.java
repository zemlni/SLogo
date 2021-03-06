package backend.commands;

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
		List<TurtleModel> turtles = getTurtlePool().getCommandableTurtleModels();
		getTurtlePool().getFrontController().startEventGrouping();
		for(TurtleModel t :turtles){
			getTurtlePool().setCurrentActiveTurtle(t.getTurtleIDNumber());
			t.homeAction();
		}		
		getTurtlePool().getFrontController().endEventGrouping();

		return turtles.get(turtles.size() - 1).getDistanceTraveled();
	}
	
}
