package commands;

import backend.BackendController;
import backend.Command;

public class ForwardCommand extends Command{
	
	public ForwardCommand(BackendController controller){
		super(controller);
		setNumArgs(1);
	}
	@Override
	public double execute() {
		double forwardAmount = getArgs().get(0).getValue();
		System.out.println("TEST" + forwardAmount);

		//TODO: do stuff for turtle.
		return forwardAmount;
		
	}

}
