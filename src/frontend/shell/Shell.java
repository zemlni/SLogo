package frontend.shell;

import frontend.app.FrontEndController;
import javafx.scene.layout.VBox;

public class Shell extends VBox {
	
	private FrontEndController frontEnd;
	
	public Shell(FrontEndController frontEnd) {
		this.frontEnd = frontEnd;
		this.getStyleClass().add("shell");
		this.setMinHeight(500);
		this.getChildren().add(new ShellTextField(this));
	}
	public void setFrontEndController(FrontEndController frontEnd) {
		this.frontEnd = frontEnd;
	}
	
	private void replaceLastLineWithLabel() {
		int size = this.getChildren().size();
		String text = ((ShellTextField) this.getChildren().get(size-1)).getText();
		this.getChildren().remove(size-1);
		this.getChildren().add(new ShellTextLabel(ShellTextField.PROMPT_STRING+" "+text));
	}
	
	public void newLine() {
		replaceLastLineWithLabel();
		this.getChildren().add(new ShellTextField(this));
	}
	
	public void appendToLabel(String text) {
		int size = this.getChildren().size();
		this.getChildren().add(size-1, new ShellTextLabel(text));
	}
	
	public void appendToField(String text) {
		int size = this.getChildren().size();
		ShellTextField field = ((ShellTextField) this.getChildren().get(size-1));
		field.setText(field.getText() + text);
	}
	
	public void evaluate(String text) {
		frontEnd.evaluate(text);
	}
		
}
