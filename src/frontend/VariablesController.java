package frontend;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class VariablesController implements VariablesControllerInterface{
	private VBox varBox;
	private Map<String, Text> variables = new HashMap<String, Text>();
	
	public VariablesController(){
		varBox = new VBox();
	}

	@Override
	public void addVariable(Variable variable) {
		Text varText = new Text();
		varText.setText(variable.getKey() + ":" + variable.getValue());
		varText.setOnMouseClicked(e -> display(new Group()));
		varBox.getChildren().add(varText);
		variables.put(variable.getKey(), varText);
	}

	@Override
	public void updateVariable(Variable updatedVariable) throws Exception {
		String target = updatedVariable.getKey();
		ObservableList<Node> varList = varBox.getChildren(); 
		
	}

	@Override
	public void removeVariable(Variable variable) throws Exception {
		for(int i = 0; i < varBox.getChildren().size(); i++){
			if(varBox.getChildren().get(i) instanceof Text){
				
			}
		}
	}
	
	
	
	public Group display(Group root){
		root.getChildren().add(varBox);
		return root;
	}

}
