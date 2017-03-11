package frontend.input.shell;

import javafx.scene.control.Label;

public class ShellTextLabel extends Label {
	public ShellTextLabel(String text) {
		super(text);
		this.getStyleClass().add("shell-text-label");
	}
}
