package commands;

import backend.Command;
import backend.Parser;

public class ForwardCommand extends Command{
	
	public ForwardCommand(Parser parser){
		super(parser);
		setNumArgs(1);
	}
	@Override
	public double execute() {
		System.out.println("TEST");
		double forwardAmount = getArgs().get(0).getValue();
		//TODO: do stuff for turtle.
		return forwardAmount;
		
	}

}
