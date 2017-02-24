package commands;

import backend.Command;

public class ForwardCommand extends Command{
	
	public ForwardCommand(){
		setNumArgs(1);
	}
	@Override
	public double execute() {
		System.out.println("TEST");
		double forwardAmount = getArgs().get(0);
		//do stuff for turtle.
		return forwardAmount;
		
	}

}
