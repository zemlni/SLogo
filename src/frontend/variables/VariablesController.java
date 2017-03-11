package frontend.variables;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import backend.Variable;
import frontend.IViewController;
import frontend.frontend.FrontEndController;
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
	public VariablesController(VariablesView view, Map<String, Variable> variables) {
		this(view);
		for (String varKey : variables.keySet()) {
			addVariable(variables.get(varKey));
		}
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
	
	public Map<String, Variable> getVariables() {
		Map<String, Variable> variables = new HashMap<>();
		for (String varKey : variableEntries.keySet()) {
			variables.put(varKey.toUpperCase(), variableEntries.get(varKey).getVariable());
		}
		return variables;
	}

	public void clear() {
		variablesBox = new VBox();
	}
	
}
