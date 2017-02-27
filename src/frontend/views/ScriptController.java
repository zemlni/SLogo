package frontend.views;

import java.io.File;
import java.io.IOException;

import frontend.app.FrontEndController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import utils.MyFileIO;


/**
 * The ScriptController is in charge of the Script tab where the user
 * can write scripts of SLogo commands that can be executed.
 * @author Matthew Tribby
 */
public class ScriptController implements InputController {
	enum FileOp {
		OPEN, SAVE
	}
	
	@FXML
	private TextArea scriptArea;
	private FrontEndController frontEnd;
	
	public void setFrontEndController(FrontEndController frontEnd) {
		this.frontEnd = frontEnd;
	}
	private String getText() { return scriptArea.getText(); }
	private void setText(String text) { scriptArea.setText(text); }
	
	
	@FXML
	private void run() {
		frontEnd.evaluate(getText());
	}
	
	@FXML
	private void clearArea() {
		setText("");
	}
	
	private File chooseFile(FileOp fileOp) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File("."));
		if (fileOp == FileOp.OPEN) {
			fileChooser.setTitle("Open");
			return fileChooser.showOpenDialog(null);
		} else {
			fileChooser.setTitle("Save As");
			return fileChooser.showSaveDialog(null);
		}
	}
	@FXML
	private void openFile() throws Exception {
		File file = chooseFile(FileOp.OPEN);
		if (file != null) {
			try {
				String scriptText = MyFileIO.readTextFile(file.getAbsolutePath());
				setText(scriptText);
			} catch (IOException e) {
				showError("Cannot open file: "+file.getName());
			}
		}
	}
	@FXML
	private void saveFile() {
		File file = chooseFile(FileOp.SAVE);
        if (file != null) {
            try {
            	MyFileIO.saveTextFile(file.getAbsolutePath(), getText());
            } catch (IOException e) {
            	showError("Cannot save file: "+file.getName());
            }
        }
	}

	
	@Override
	public void showError(String error) {
		
	}
	@Override
	public void showText(String text) {
		
	}
	
}
