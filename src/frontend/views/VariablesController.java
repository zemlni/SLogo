package frontend.views;

import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;

import backend.Variable;
import frontend.app.FrontEndController;
import frontend.variables.VariableEntry;
import javafx.scene.layout.VBox;


/**
 * To handle the front-end needs for showing variables in the variable 
 * environment window for the user.
 * @author Matthew Tribby
 */
public class VariablesController {
	private Map<String, VariableEntry> variableEntries;
	FrontEndController frontEnd;

	@FXML
	private VBox variablesBox;
	
	public VariablesController(){
		variableEntries = new HashMap<String, VariableEntry>();
	}
	
	public void setFrontEndController(FrontEndController frontEndController){
		frontEnd =frontEndController;
	}
	
	public void addVariable(Variable variable) {
		if(!variableEntries.containsKey(variable.getKey())){
			VariableEntry varEntry = new VariableEntry(variable);
			variablesBox.getChildren().add(varEntry);
			variableEntries.put(variable.getKey(), varEntry);
		}
		else{
			variableEntries.get(variable.getKey()).changeValue(variable.getValue());
		}
	}

	//Currently there is no need for this method so not implemented. Might remove from the 
	//API, will decide based on second set of implementation goals
	public void removeVariable(Variable variable) throws Exception {
		
	}
	
}
