package frontend.views;

import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;

import backend.Variable;
import frontend.app.FrontEndController;
import frontend.variables.VariableEntry;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 * To handle the front-end needs for showing variables in the variable 
 * environment window for the user.
 * @author Matthew Tribby
 */
public class VariablesController {
	private FrontEndController frontEnd;
	private Map<String, VariableEntry> variableEntries;

	@FXML
	private VBox variablesBox;
	
	public VariablesController(){
		variableEntries = new HashMap<String, VariableEntry>();
	}
	
	public void setFrontEndController(FrontEndController frontEnd) {
		this.frontEnd = frontEnd;
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
			variableEntries.remove(variable.getKey());
			for(int i = 0; i < variablesBox.getChildren().size(); i++){
				if(variablesBox.getChildren().get(i) instanceof Text){
					String varName = ((Text) variablesBox.getChildren().get(i)).getText();
					varName = varName.substring(0, varName.indexOf(" "));
					if(varName.equals(variable.getKey())){
						variablesBox.getChildren().remove(i);
					}
				}
			}
		}
		else{
			throw new Exception();
		}
	}
	
}
