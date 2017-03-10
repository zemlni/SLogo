package backend.turtlecommands;

import java.util.ArrayList;
import java.util.List;

import backend.BackendController;
import backend.parser.Expression;
import backend.parser.Input;

public class AskCommand extends TurtleCommand {
	public AskCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}
	
	@Override
	public double execute(){
		List<Expression> tellList = getChildren().get(0).getChildren();
		List<Integer> integerTellList = new ArrayList<Integer>();
		for(Expression e: tellList){
			int id = (int) e.evaluate().getValue();
			if(!getTurtlePool().cointainsTurtle(id)){
				getTurtlePool().addTurtleUpTo(id);
			}
			System.out.println("Hello" + id);
			integerTellList.add(id);
			getTurtlePool().setCurrentActiveTurtle(id);
		}
		getTurtlePool().setTurtleListToSpecified(integerTellList);

		return getTurtlePool().getCurrentActiveTurtleID();
	}
}
