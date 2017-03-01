package frontend.views;

import backend.Variable;
import javafx.scene.text.Text;

public class VariableEntry {
	String variableName;
	Text variableText;

	public VariableEntry(Variable variable){
		variableName = variable.getKey();
		variableText = new Text();
		variableText.setText(variableName + "			" + variable.getValue());
		variableText.setOnMouseClicked(e -> changeValue());
	}
	
	public Text getText(){
		return variableText;
	}
	
	private void changeValue(){
		new VariableEditor(this);
	}
	
	public String getName(){
		return variableName;
	}
	
	public void changeValue(String value){
		variableText.setText(variableName + "			" + value);
	}
}
