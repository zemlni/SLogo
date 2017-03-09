package frontend.views;

import java.util.HashMap;
import java.util.Map;

import backend.Variable;
import frontend.app.FrontEndController;
import frontend.nonfxml.view.IViewController;
import frontend.nonfxml.view.VariablesView;
import frontend.variables.VariableEntry;
import javafx.scene.layout.VBox;


/**
 * To handle the front-end needs for showing variables in the variable 
 * environment window for the user.
 * @author Matthew Tribby
 */
public class VariablesController implements IViewController {
	private Map<String, VariableEntry> variableEntries;
	FrontEndController frontEnd;

	private VBox variablesBox;
	
	public VariablesController(VariablesView view) {
		variablesBox = view.getVariablesBox();
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

	public void removeVariable(Variable variable) throws Exception {
		
		if(variableEntries.containsKey(variable.getKey())){
			variablesBox.getChildren().remove(variableEntries.get(variable.getKey()));
			variableEntries.remove(variable.getKey());
		}
		
		//String targetName = variable.getKey();
		
		/*
		if(variableEntries.containsKey(targetName)){
			for(int i = 0; i < variablesBox.getChildren().size(); i++){
				if(targetName.equals(variablesBox.getChildren().get(i))){
					variablesBox.getChildren().remove(i);
					variableEntries.remove(targetName);
				}
			}
		}
		*/
	}
	
}
