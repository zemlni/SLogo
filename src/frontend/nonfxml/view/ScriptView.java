package frontend.nonfxml.view;

import frontend.views.ScriptController;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import utils.javafx.FX;

public class ScriptView extends AnchorPane implements InputView {

	private TextArea scriptArea;
	private ScriptController controller;
	
	public ScriptView() {
		AnchorPane textPane = new AnchorPane();
		FX.setAnchors(textPane, 0.0, 60.0, 0.0, 0.0);

		scriptArea = new TextArea();
		FX.setAnchors(scriptArea, 5.0, 5.0, 5.0, 5.0);
		
		textPane.getChildren().add(scriptArea);

		controller = new ScriptController(this);
		
		Button runBtn = FX.button("Run", e -> controller.run());
		Button clearBtn = FX.button("Clear", e -> controller.clearArea());
		Button openBtn = FX.button("Open", e -> controller.openFile());
		Button saveBtn = FX.button("Save", e -> controller.saveFile());
		
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
