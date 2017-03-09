package backend.commands;

import backend.BackendController;
import backend.parser.Input;

public class HideTurtleCommand extends TurtleCommand{

	public HideTurtleCommand(Input in, BackendController controller) {
		super(in, controller, 0);
	}

	@Override
	public double execute() {
		updateTurtleVisibility();
		
		return 0;
	}

	private void updateTurtleVisibility() {
		
		updateTurtleModel();
		updateTurtleView();
	}
	
	private void updateTurtleView() {
		getTurtle().getFrontController().hideTurtle();
	}

	private void updateTurtleModel(){
		getTurtle().setInvis();
	}
	

}
