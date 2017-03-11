package frontend.input.shell;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;

public class ShellTextField extends HBox {
	public static final String PROMPT_STRING = ">>";
	
	private Shell shell;
	private ShellTextLabel label;
	private TextField field;
	
	public ShellTextField(Shell shell) {
		this.shell = shell;
		this.getStyleClass().add("shell-text-field-hbox");
		label = new ShellTextLabel(PROMPT_STRING);
		field = new TextField();
		field.setPrefWidth(500);
		field.getStyleClass().add("shell-text-field");
		field.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				this.shell.newLine();
				this.shell.evaluate(getText());
			}
		});
		this.getChildren().addAll(label, field);
	}
	
	public String getText() {
		return field.getText();
	}
	public void setText(String text) {
		field.setText(text);
	}

	
}
