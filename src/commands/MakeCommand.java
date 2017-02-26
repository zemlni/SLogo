package commands;

import backend.Command;
import backend.Parser;

public class MakeCommand extends Command {

	public MakeCommand(Parser parser){
		super(parser);
		setNumArgs(2);
	}
	@Override
	public double execute() {
		//or add to variable table in here. 
		// TODO Auto-generated method stub
		return 0;
	}

}
