package frontend.animation;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import language.Language;

public class AlertEvent extends AnimatedEvent {

	private String errorMsg;
	private String bad;
	
	public AlertEvent(String errorMsg, String bad) {
		this.errorMsg = errorMsg;
		this.bad = bad;
	}
	
	@Override
	public double update(double dt) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(Language.getWord("ErrorTitle"));
		alert.setContentText(Language.getWord(errorMsg) + bad);
		alert.showAndWait();
		setFinishedTrue();
		return dt;
	}

}
