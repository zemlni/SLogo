package frontend.input.shell;

import frontend.frontend.FrontEndController;
import javafx.scene.layout.VBox;

public class Shell extends VBox {
	
	private FrontEndController frontEnd;
	
	public Shell() {
		this.getStyleClass().add("shell");
		this.getChildren().add(new ShellTextField(this));
	}
	public void setFrontEndController(FrontEndController frontEnd) {
		this.frontEnd = frontEnd;
	}
	
	private void replaceLastLineWithLabel() {
		String text = ((ShellTextField) this.getChildren().get(size()-1)).getText();
		this.getChildren().remove(size()-1);
		this.getChildren().add(new ShellTextLabel(ShellTextField.PROMPT_STRING+" "+text));
	}
	
	public void newLine() {
		replaceLastLineWithLabel();
		this.getChildren().add(new ShellTextField(this));
	}
	
	private int size() {
	    return this.getChildren().size();
	}
	public void appendToLabel(String text) {
		this.getChildren().add(size()-1, new ShellTextLabel(text));
		setFocus();
	}
	public void appendToField(String text) {
		ShellTextField field = ((ShellTextField) this.getChildren().get(size()-1));
		field.setText(field.getText() + text);
		setFocus();
	}
	
	public void evaluate(String text) {
		frontEnd.evaluate(text);
	}
		
	private void setFocus() {
	    this.getChildren().get(size()-1).requestFocus();
	}
	
}
