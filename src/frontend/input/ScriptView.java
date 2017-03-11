package frontend.input;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import utils.javafx.FX;

public class ScriptView extends AnchorPane implements InputView {

	private TextArea scriptArea;
	private TextArea breakPointArea;
	private ScriptController controller;
	
	public ScriptView() {
		AnchorPane textPane = new AnchorPane();
		FX.setAnchors(textPane, 0.0, 60.0, 0.0, 0.0);
		
		scriptArea = new TextArea();
		FX.setAnchors(scriptArea, 5.0, 5.0, 5.0, 70.0);

		breakPointArea = new TextArea();
		AnchorPane.setLeftAnchor(breakPointArea, 5.0);
		AnchorPane.setTopAnchor(breakPointArea, 5.0);
		AnchorPane.setBottomAnchor(breakPointArea, 5.0);
		breakPointArea.setPrefWidth(57);
		breakPointArea.prefHeightProperty().bind(scriptArea.heightProperty());
		breakPointArea.setWrapText(true);
		
		textPane.getChildren().addAll(scriptArea, breakPointArea);

		controller = new ScriptController(this);
		
		Button runBtn = FX.button("Run", e -> controller.run());
		Button clearBtn = FX.button("Clear", e -> controller.clearArea());
		Button openBtn = FX.button("Open", e -> controller.openFile());
		Button saveBtn = FX.button("Save", e -> controller.saveFile());
		Button debugBtn = FX.button("Debug", e -> controller.debug());
		Button stepBtn = FX.button("Step", e -> controller.step());
		
		VBox buttonsBox = new VBox();
		buttonsBox.getChildren().addAll(
				runBtn, clearBtn, openBtn, saveBtn, debugBtn, stepBtn
				);
		AnchorPane.setRightAnchor(buttonsBox, 0.0);
		
		this.getChildren().addAll(textPane, buttonsBox);
	}

	public String getBreakPointsText() {
		return breakPointArea.getText();
	}
	public String getScriptText() {
		return scriptArea.getText();
	}
	public void setScriptText(String text) {
		scriptArea.setText(text);
	}
	
	@Override
	public ScriptController getController() {
		return controller;
	}
	
}
