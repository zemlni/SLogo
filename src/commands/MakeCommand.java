package commands;

import backend.BackendController;
import backend.Command;
import backend.Parser;

public class MakeCommand extends Command {

	public MakeCommand(BackendController controller){
		super(controller);
		setNumArgs(2);
	}
	@Override
	public double execute() {
		//or add to variable table in here. 
		// TODO Auto-generated method stub
		return 0;
	}

}
