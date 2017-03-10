package backend.turtlecommands;

import java.util.List;

import backend.BackendController;
import backend.Variable;
import backend.parser.Input;
import backend.turtle.TurtleModel;
import frontend.app.FrontEndController;

public class ForwardCommand extends TurtleCommand {

	public ForwardCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}
	
	@Override
	public double execute() {
		double forwardAmount = 0;
		List<TurtleModel> turtles = getTurtlePool().getCommandableTurtleModels();
		getTurtlePool().getFrontController().startEventGrouping();
		for (TurtleModel t : turtles) {
			getTurtlePool().setCurrentActiveTurtle(t.getTurtleIDNumber());
			for (Variable var: getArgs()) {
				System.out.println("TEST" + getTurtlePool().getCurrentActiveTurtleID());
				forwardAmount = var.getValue();
				t.moveForwardsAction(forwardAmount);
			}
		}
		
		getTurtlePool().getFrontController().endEventGrouping();
		return forwardAmount;
	}
}

