package backend.turtlecommands;

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
		for (Variable var : getArgs()) {
			deltaDir = var.getValue();
			List<TurtleModel> turtles = getTurtlePool().getActiveTurtles();
			for(TurtleModel t :turtles){
				getTurtlePool().setCurrentActiveTurtle(t.getTurtleIDNumber());
				t.rightAction(deltaDir);
			}
		}
		return deltaDir;
	}
}
