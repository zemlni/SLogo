package frontend.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import backend.Variable;
import frontend.app.FrontEndController;
import frontend.nonfxml.IViewController;
import frontend.nonfxml.view.VariablesView;
import frontend.variables.VariableEntry;
import javafx.scene.Node;
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
	
	public void addVariable(List<Variable> variables) {
		variables.forEach(this::addVariable);
	}

	public void removeVariable(Variable variable) throws Exception {
		
		if(variableEntries.containsKey(variable.getKey())){
			variablesBox.getChildren().remove(variableEntries.get(variable.getKey()));
			variableEntries.remove(variable.getKey());
		}
	}
	
	public List<Variable> getVariables() {
		List<Variable> variables = new ArrayList<>();
		for (Node entry : variablesBox.getChildren()) {
			variables.add(((VariableEntry) entry).getVariable());
		}
		return variables;
	}

	public void clear() {
		variablesBox = new VBox();
	}
	
}
