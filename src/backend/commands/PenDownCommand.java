package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.parser.Input;
import backend.turtle.TurtleModel;

public class PenDownCommand extends TurtleCommand{

	public PenDownCommand(Input in, BackendController controller) {
		super(in, controller, 0);
	}

	@Override
	public double execute() {
		List<TurtleModel> turtles = getTurtlePool().getCommandableTurtleModels();
		for(TurtleModel t :turtles){
			getTurtlePool().setCurrentActiveTurtle(t.getTurtleIDNumber());
			t.penDownAction();
		}
		return 1;
	}

}
