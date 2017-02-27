package frontend.views;

import frontend.app.FrontEndController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ScriptController {

	@FXML
	private TextArea scriptArea;
	
	private FrontEndController frontEnd;
	public void setFrontEndController(FrontEndController frontEnd) {
		this.frontEnd = frontEnd;
	}
	
}
