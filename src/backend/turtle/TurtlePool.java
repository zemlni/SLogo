package backend.turtle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import frontend.app.FrontEndController;


public class TurtlePool {
	private Map<Integer, TurtleModel> turtleMap;
	private List<Integer> commandableTurtleList;
	private List<Integer> storedActiveTurtleList;
	private FrontEndController fcontrol;
	
	
	public TurtlePool(FrontEndController c){
		turtleMap = new HashMap<Integer, TurtleModel>();
		fcontrol = c;
	}

	/*
	 * adds new turtles up to the id
	 */
	public void addTurtleUpTo(int id){
		if(!turtleMap.containsKey(id)){
			for(int currID = highestTurtleID() + 1; currID <= id; currID++){
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
		storedActiveTurtleList = commandableTurtleList;
	}
	
	public void restoreTurtleListToActive(){
		commandableTurtleList = storedActiveTurtleList;
	}
	
//	public List<TurtleModel> getSpecifiedTurtles(List<Integer> turtleIDList){
//		List<TurtleModel> turtleList = new ArrayList<TurtleModel>();
//		for(int specifiedID : turtleIDList){
//			turtleList.add(retrieveTurtleFromMap(specifiedID));
//		}
//		return turtleList;
//	}
}
