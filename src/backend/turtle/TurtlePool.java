package backend.turtle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import frontend.app.FrontEndController;


public class TurtlePool {
	private int currentActiveTurtle;
	private Map<Integer, TurtleModel> turtleMap;
	private List<Integer> commandableTurtleList;
	private List<Integer> storedCommandableTurtleList;
	private FrontEndController fcontrol;
	private int totalTurtles;
	
	
	public TurtlePool(FrontEndController c){
		turtleMap = new HashMap<Integer, TurtleModel>();
		totalTurtles = 0;
		fcontrol = c;
		currentActiveTurtle = 0;
	}
	
	public void setCurrentActiveTurtle(int active){
		currentActiveTurtle = active;
	}
	
	public int getCurrentActiveTurtleID(){
		return currentActiveTurtle;
	}
	
	public TurtleModel getTurtle(int id){
		return turtleMap.get(id);
	}
	
	public int getTotalTurtles(){
		return totalTurtles;
	}
	/*
	 * adds new turtles up to the id
	 */
	public void addTurtleUpTo(int id){
		if(!turtleMap.containsKey(id)){
			for(int currID = highestTurtleID() + 1; currID <= id; currID++){
				totalTurtles++;
				addToTurtleMap(currID);
				addToCommandableTurtleList(currID);
			}
		}
	}
	
	private void addToTurtleMap(int id){
		turtleMap.put(id, new TurtleModel(id, fcontrol));
	}
	
	private void addToCommandableTurtleList(int id){
		commandableTurtleList.add(id);
	}
	
	private int highestTurtleID(){
		return turtleMap.size();
	}
	
	private TurtleModel retrieveTurtleFromMap(int id){
		return turtleMap.get(id);
	}
	
	// TurtlePool.getActiveTurtles()
	// foreach(turtleModel turtle : turtleList)
	public List<TurtleModel> getActiveTurtles(){
		List<TurtleModel> turtleList = new ArrayList<TurtleModel>();
		for(int activeTurtleID : commandableTurtleList){
			turtleList.add(retrieveTurtleFromMap(activeTurtleID));
		}
		return turtleList;
	}
	
	public void setTurtleListToSpecified(List<Integer> turtleIDList){
		storeCurrentlyActive();
		commandableTurtleList = turtleIDList;
	}
	
	private void storeCurrentlyActive(){
		storedCommandableTurtleList = commandableTurtleList;
	}
	
	public void restoreTurtleListToActive(){
		commandableTurtleList = storedCommandableTurtleList;
	}
	
}
