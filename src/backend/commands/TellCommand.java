package backend.commands;

import backend.BackendController;
import backend.commands.abstracts.AskTellCommand;
import backend.parser.Input;

public class TellCommand extends AskTellCommand{
	public TellCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}
	
	@Override
	public double execute(){
		loopChildrenDoSomething();
		// TODO: call the frontend api to tell it which turtles are "selected"
		//frontendmethod(getTurtlePool().getCommandableTurtleList());
		return getTurtlePool().getCurrentActiveTurtleID();
	}
}
