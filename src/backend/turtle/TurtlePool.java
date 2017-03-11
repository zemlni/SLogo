package backend.turtle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import frontend.frontend.FrontEndController;


public class TurtlePool {
	private int currentActiveTurtle;
	private Map<Integer, TurtleModel> turtleMap;
	private List<Integer> commandableTurtleList;
	private List<Integer> storedCommandableTurtleList;
	private FrontEndController fcontrol;
	private int totalTurtles;
	
	
	public TurtlePool(FrontEndController c){
		turtleMap = new HashMap<Integer, TurtleModel>();
		commandableTurtleList = new ArrayList<Integer>();
		storedCommandableTurtleList = new ArrayList<Integer>();
		totalTurtles = 0;
		fcontrol = c;
		currentActiveTurtle = 0;
		addTurtleUpTo(1); //initialize with 1 turtle already on the screen
	}
	
	public List<TurtleModel> getAllTurtleModels(){
		List<TurtleModel> turtles = new ArrayList<TurtleModel>();
		for(TurtleModel t : turtleMap.values()){
			turtles.add(t);
		}
		return turtles;
		
	}
	
	public FrontEndController getFrontController(){
		return fcontrol;
	}
	
	public void setCurrentActiveTurtle(int active){
		currentActiveTurtle = active;
	}
	
	public int getCurrentActiveTurtleID(){
		return currentActiveTurtle;
	}
	
	public boolean cointainsTurtle(int id){
		return turtleMap.containsKey(id);
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
				currentActiveTurtle = currID;
				fcontrol.addTurtle(currID);
				
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
	
	public List<TurtleModel> getCommandableTurtleModels(){
		List<TurtleModel> turtleList = new ArrayList<TurtleModel>();
		for(int activeTurtleID : commandableTurtleList){
			turtleList.add(retrieveTurtleFromMap(activeTurtleID));
		}
		return turtleList;
	}
	
	public void setTurtleListToSpecified(List<Integer> turtleIDList){
		storeCurrentlyActive();
		commandableTurtleList = turtleIDList;
		fcontrol.updateCommandable(commandableTurtleList);
	}
	
	private void storeCurrentlyActive(){
		storedCommandableTurtleList = commandableTurtleList;
	}
	
	public void restoreTurtleListToOriginalCommandable(){
		commandableTurtleList = storedCommandableTurtleList;
		fcontrol.updateCommandable(commandableTurtleList);
	}
	
//	public void setPenDown(int id){
//		turtleMap.get(id).setPenDown();
//	}
//	
//	public void setPenUp(int id){
//		turtleMap.get(id).setPenUp();
//	}
	
	public void setAllPenUp(){
		for(TurtleModel t :turtleMap.values()){
			t.setPenUp();
		}
	}
	public void setAllPenDown(){
		for(TurtleModel t :turtleMap.values()){
			t.setPenDown();
		}
	}
	
	public void toggleTurtle(int id){
		if(commandableTurtleList.contains(id)){
			commandableTurtleList.remove((Integer) id);
		} else {
			commandableTurtleList.add(id);
		}
		
	}
	
	public List<Integer> getCommandableTurtleIntegers(){
		return commandableTurtleList;
	}
}
