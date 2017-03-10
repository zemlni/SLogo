package backend.turtlecommands;

import java.util.ArrayList;
import java.util.List;

import backend.BackendController;
import backend.Variable;
import backend.parser.Expression;
import backend.parser.Input;

public class TellCommand extends TurtleCommand{
	public TellCommand(Input in, BackendController controller) {
		super(in, controller, 1);
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
			integerTellList.add(id);
			getTurtlePool().setCurrentActiveTurtle(id);
		}
		getTurtlePool().setTurtleListToSpecified(integerTellList);
		// TODO: call the frontend api to tell it which turtles are "selected"
		//frontendmethod(getTurtlePool().getCommandableTurtleList());
		return getTurtlePool().getCurrentActiveTurtleID();
	}
}
