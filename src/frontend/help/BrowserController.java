package frontend.help;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class BrowserController {

	@FXML
	private WebView browser;
	private WebEngine webEngine;
	@FXML
	private TextField addressField;
	@FXML
	private Button nextBtn;
	@FXML
	private Button prevBtn;
	
	
	@FXML
	private void initialize() {
		webEngine = browser.getEngine();
	}
	public void goToURL(String url) {
		webEngine.load(url);
	}
	
	@FXML
	private void nextPage() {
		Platform.runLater(() -> {
			webEngine.executeScript("history.forward()");
		});
	}
	@FXML
	private void prevPage() {
		Platform.runLater(() -> {
			webEngine.executeScript("history.back()");
		});
	}
	@FXML
	private void goToAddress() {
		webEngine.load(addressField.getText());
	}
	
}
