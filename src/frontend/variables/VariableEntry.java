package frontend.variables;

import backend.Variable;
import javafx.scene.control.Button;

public class VariableEntry extends Button{
	private String variableName;
	private Variable variable;
	public static final String NAME_VALUE_SPACE = "			";

	public VariableEntry(Variable variable){
		super(variable.getKey() + NAME_VALUE_SPACE + variable.getValue());
		
		this.variable = variable;
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
	public Variable getVariable() {
		return variable;
	}
	
	public void changeValue(String value){
		changeValue(Double.parseDouble(value));
	}
	
	
	public void changeValue(double value){
		setText(variableName + NAME_VALUE_SPACE + value);
		variable.update(value);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof VariableEntry){
			VariableEntry comparison = (VariableEntry) o;
		
			if(comparison.variableName.equals(this.variableName)){
				return true;
			}
		}
		return false;
		
	}

}
