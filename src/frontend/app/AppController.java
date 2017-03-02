package frontend.app;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import language.LanguageSetter;

public class AppController {

	@FXML
	private AnchorPane anchorPane;
	@FXML
	private MenuController topMenuController;
	@FXML
	private SessionsController sessionsController;
	private LanguageSetter languageSetter;
	
	public AppController() {
		// An fxml controller must have a public no-args constructor
		// that does nothing.
		// This controller can be omitted, but note that declaring one
		// with-args constructor without explicitly declaring the no-args
		// constructor will make the no-args constructor disappear.
	}
	@FXML
	private void initialize() {
		// Do the initialization here (instead of inside the no-args constructor).
		topMenuController.setAppController(this);
		sessionsController.setAppController(this);
		languageSetter = new LanguageSetter();
	}
	
	public void addNewSession() throws IOException {
		sessionsController.addNewSession();
	}
	public void changeLanguageTo(String language) {
		 // TODO: use something better than if 
		if (language.equals("中文")) {
			languageSetter.setLanguage("Chinese");
		} else if (language.equals("English")) {
			languageSetter.setLanguage("English");
		}
	}
	
}
