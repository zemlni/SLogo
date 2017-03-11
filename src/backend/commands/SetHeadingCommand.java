package backend.commands;

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
		List<TurtleModel> turtles = getTurtlePool().getCommandableTurtleModels();
		getTurtlePool().getFrontController().startEventGrouping();

		for(TurtleModel t :turtles){
			getTurtlePool().setCurrentActiveTurtle(t.getTurtleIDNumber());

			for (Variable var : getArgs()) {
				double heading = var.getValue();
				t.setHeadingAction(heading);
			}
		}
		getTurtlePool().getFrontController().endEventGrouping();

		return turtles.get(turtles.size() - 1).getAngleTurned();
	}
}
