package frontend.views;

import java.util.Optional;

import javafx.scene.control.TextInputDialog;

public class VariableEditor {
	VariableEntry entry;
	
	public VariableEditor(VariableEntry varEntry){
		//http://code.makery.ch/blog/javafx-dialogs-official/
		entry = varEntry;
		TextInputDialog valueInput = new TextInputDialog();
		valueInput.setContentText("Enter desired double value for " + entry.getName());
		valueInput.setTitle("Value Editor for " + entry.getName());
		Optional<String> result = valueInput.showAndWait();
		result.ifPresent(e -> handleResult(result.get()));
	}
	
	private void handleResult(String number){
		//Regular Expressions from http://stackoverflow.com/questions/14206768/how-to-check-if-a-string-is-numeric
		Boolean match = number.matches("[-+]?\\d*\\.?\\d+");  
		if(match){
			entry.changeValue(number);
		}
	}
}
