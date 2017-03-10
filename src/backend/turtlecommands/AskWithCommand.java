package backend.turtlecommands;

import java.util.ArrayList;
import java.util.List;

import backend.BackendController;
import backend.parser.Input;
import backend.turtle.TurtleModel;

public class AskWithCommand extends TurtleCommand{
	public AskWithCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}
	
	@Override
	public double execute(){
		System.out.println("hi");
		List<Integer> integerTellList = new ArrayList<Integer>();
		System.out.println("hi1");

		List<TurtleModel> allTurtleModels = getTurtlePool().getAllTurtleModels();
		System.out.println("hi2");

		System.out.println(allTurtleModels.size());
		for(TurtleModel t: allTurtleModels){
			System.out.println("hi");
			getTurtlePool().setCurrentActiveTurtle(t.getTurtleIDNumber());

			boolean condition = getChildren().get(0).getChildren().get(0).evaluate().getValue() == 1;
			if(condition){
				integerTellList.add(t.getTurtleIDNumber());
			}
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
