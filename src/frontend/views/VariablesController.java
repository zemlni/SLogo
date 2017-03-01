package frontend.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.fxml.FXML;

import backend.Variable;
import frontend.app.FrontEndController;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 * To handle the front-end needs for showing variables in the variable 
 * environment window for the user.
 * @author Matthew Tribby
 */
public class VariablesController {
	private FrontEndController frontEnd;
	private List<Variable> variables;
	
	@FXML
	private VBox variablesBox;
	
	public void setFrontEndController(FrontEndController frontEnd) {
		this.frontEnd = frontEnd;
		variables = new ArrayList<Variable>();
	}
	
	public void addVariable(Variable variable) {
		Text varText = new VariableEntry(variable).getText();
		variablesBox.getChildren().add(varText);
		variables.add(variable);
	}

	public void removeVariable(Variable variable) throws Exception {
		for(int i = 0; i < variablesBox.getChildren().size(); i++){
			if(variablesBox.getChildren().get(i) instanceof Text){
				
			}
		}
	}
	
}
