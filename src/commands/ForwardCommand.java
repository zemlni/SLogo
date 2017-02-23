package commands;

import backend.Command;

public class ForwardCommand extends Command{
	
	public ForwardCommand(){
		setNumArgs(1);
	}
	@Override
	public double execute() {
		System.out.println("TEST");
		// TODO Auto-generated method stub
		return 0;
		
	}

}
