package frontend.views;

import java.io.File;
import java.io.IOException;

import frontend.app.FrontEndController;
import frontend.nonfxml.view.IViewController;
import frontend.nonfxml.view.ScriptView;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import utils.MyFileIO;


/**
 * The ScriptController is in charge of the Script tab where the user
 * can write scripts of SLogo commands that can be executed.
 * @author Matthew Tribby
 */
public class ScriptController implements InputController, IViewController {
	enum FileOp {
		OPEN, SAVE
	}
	
	private TextArea scriptArea;
	private FrontEndController frontEnd;
	
	public ScriptController(ScriptView view){
		scriptArea = view.getScriptArea();
	}
	
	public void setFrontEndController(FrontEndController frontEnd) {
		this.frontEnd = frontEnd;
	}
	private String getText() { return scriptArea.getText(); }
	private void setText(String text) { scriptArea.setText(text); }
	
	
	public void run() {
		frontEnd.evaluate(getText());
	}
	
	public void clearArea() {
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

	public void openFile() {
		File file = chooseFile(FileOp.OPEN);
		if (file != null) {
			try {
				String scriptText = MyFileIO.readTextFile(file.getAbsolutePath());
				setText(scriptText);
			} catch (IOException e) {
				showError("FileOpenError", file.getName());
			}
		}
	}

	public void saveFile() {
		File file = chooseFile(FileOp.SAVE);
        if (file != null) {
            try {
            	MyFileIO.saveTextFile(file.getAbsolutePath(), getText());
            } catch (IOException e) {
            	showError("FileSaveError", file.getName());
            }
        }
	}

	
	@Override
	public void showError(String error, String bad) {
		frontEnd.showErrorAlert(error, bad);
	}
	@Override
	public void showText(String text) {
		
	}
	@Override
	public void appendText(String text) {
		setText(getText()+text+System.lineSeparator());
	}
	
}
