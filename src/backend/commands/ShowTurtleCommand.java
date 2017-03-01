package backend.commands;

import backend.BackendController;

public class ShowTurtleCommand extends TurtleCommand{
	public ShowTurtleCommand(BackendController controller) {
		super(controller, 0);
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
		getTurtle().getFrontController().showTurtle();
	}

	private void updateTurtleModel(){
		getTurtle().setVis();
	}
}
