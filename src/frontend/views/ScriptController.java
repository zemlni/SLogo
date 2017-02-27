package frontend.views;

import frontend.app.FrontEndController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;


/**
 * The ScriptController is in charge of the Script tab where the user
 * can write scripts of SLogo commands that can be executed.
 * @author Matthew Tribby
 */
public class ScriptController implements InputController {

	@FXML
	private TextArea scriptArea;
	
	private FrontEndController frontEnd;
	public void setFrontEndController(FrontEndController frontEnd) {
		this.frontEnd = frontEnd;
	}
	
	@Override
	public void showError(String error) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showText(String text) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void load(String filename) throws Exception {
		
	}

	public void saveAs(String filename) {
		
	}

	@FXML
	private void run() {
		frontEnd.evaluate(scriptArea.getText());
	}
	
	
}
