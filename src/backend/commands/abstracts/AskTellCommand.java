package backend.commands.abstracts;

import java.util.ArrayList;
import java.util.List;

import backend.BackendController;
import backend.commands.TurtleCommand;
import backend.parser.Expression;
import backend.parser.Input;

public class AskTellCommand extends TurtleCommand {
	
	public AskTellCommand(Input in, BackendController controller, int numChild) {
		super(in, controller, numChild);
	}
	
	protected void loopChildrenDoSomething(){
		List<Expression> tellList = getChildren().get(0).getChildren();
		List<Integer> integerTellList = new ArrayList<Integer>();
		for(Expression e: tellList){
			int id = (int) e.evaluate().getValue();
			if(!getTurtlePool().cointainsTurtle(id)){
				getTurtlePool().addTurtleUpTo(id);
			}
			integerTellList.add(id);
			getTurtlePool().setCurrentActiveTurtle(id);
		}
		getTurtlePool().setTurtleListToSpecified(integerTellList);

	}
	
}
