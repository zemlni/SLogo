package frontend.nonfxml.view;

import frontend.views.ScriptController;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import language.Language;

public class ScriptView extends AnchorPane implements IControllableView {

	private TextArea scriptArea;
	private ScriptController controller;
	
	public ScriptView() {
		AnchorPane textPane = new AnchorPane();
		AnchorPane.setTopAnchor(textPane, 0.0);
		AnchorPane.setLeftAnchor(textPane, 0.0);
		AnchorPane.setBottomAnchor(textPane, 0.0);
		AnchorPane.setRightAnchor(textPane, 60.0);
		
		scriptArea = new TextArea();
		AnchorPane.setTopAnchor(scriptArea, 5.0);
		AnchorPane.setLeftAnchor(scriptArea, 5.0);
		AnchorPane.setBottomAnchor(scriptArea, 5.0);
		AnchorPane.setRightAnchor(scriptArea, 5.0);
		
		textPane.getChildren().add(scriptArea);

		controller = new ScriptController(this);
		
		Button runBtn = new Button();
		runBtn.textProperty().bind(Language.createStringBinding("Run"));
		runBtn.setOnAction(e -> controller.run());
		Button clearBtn = new Button();
		clearBtn.textProperty().bind(Language.createStringBinding("Clear"));
		clearBtn.setOnAction(e -> controller.clearArea());
		Button openBtn = new Button();
		openBtn.textProperty().bind(Language.createStringBinding("Open"));
		openBtn.setOnAction(e -> controller.openFile());
		Button saveBtn = new Button();
		saveBtn.textProperty().bind(Language.createStringBinding("Save"));
		saveBtn.setOnAction(e -> controller.saveFile());
		
		VBox buttonsBox = new VBox();
		buttonsBox.getChildren().addAll(runBtn, clearBtn, openBtn, saveBtn);
		AnchorPane.setRightAnchor(buttonsBox, 0.0);
		
		this.getChildren().addAll(textPane, buttonsBox);
	}

	public TextArea getScriptArea() {
		return scriptArea;
	}
	
	@Override
	public ScriptController getController() {
		return controller;
	}
	
}
