package backend.commands;

import java.util.ArrayList;
import java.util.List;

import backend.BackendController;
import backend.parser.Expression;
import backend.parser.Input;
import backend.turtle.TurtleModel;

public class AskCommand extends TurtleCommand {
	public AskCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}
	
	@Override
	public double execute(){
		List<Expression> commandableList = getChildren().get(0).getChildren();
		List<Integer> integerTellList = new ArrayList<Integer>();
		for(Expression e: commandableList){
			int id = (int) e.evaluate().getValue();
			if(!getTurtlePool().cointainsTurtle(id)){
				getTurtlePool().addTurtleUpTo(id);
			}
			integerTellList.add(id);
			getTurtlePool().setCurrentActiveTurtle(id);
		}
		getTurtlePool().setTurtleListToSpecified(integerTellList);
		
		List<TurtleModel> turtles = getTurtlePool().getCommandableTurtleModels();
		for (TurtleModel t : turtles) {
			getTurtlePool().setCurrentActiveTurtle(t.getTurtleIDNumber());
			getArgs();
		}
		
		getTurtlePool().restoreTurtleListToOriginalCommandable();
		return getTurtlePool().getCurrentActiveTurtleID();
	}
}
