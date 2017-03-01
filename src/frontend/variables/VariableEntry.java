package frontend.variables;

import backend.Variable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class VariableEntry extends Button{
	private String variableName;
	public static final String NAME_VALUE_SPACE = "			";

	public VariableEntry(Variable variable){
		super(variable.getKey() + NAME_VALUE_SPACE + variable.getValue());
		
		variableName = variable.getKey();
		setOnMouseClicked(e -> changeValue());
		this.getStyleClass().add("table-entry");
	}
	
	private void changeValue(){
		new VariableEditor(this);
	}
	
	public String getName(){
		return variableName;
	}
	
	public void changeValue(String value){
		setText(variableName + NAME_VALUE_SPACE + value);
	}
	
	public void changeValue(double value){
		setText(variableName + NAME_VALUE_SPACE + value);
	}
}
