package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.commands.abstracts.AskTellCommand;
import backend.parser.Input;
import backend.turtle.TurtleModel;

public class AskCommand extends AskTellCommand {
	public AskCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}
	
	@Override
	public double execute(){
		loopChildrenDoSomething();
		
		List<TurtleModel> turtles = getTurtlePool().getCommandableTurtleModels();
		for (TurtleModel t : turtles) {
			getTurtlePool().setCurrentActiveTurtle(t.getTurtleIDNumber());
			getArgs();
		}
		
		getTurtlePool().restoreTurtleListToOriginalCommandable();
		return getTurtlePool().getCurrentActiveTurtleID();
	}
}
