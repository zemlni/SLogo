package frontend.app;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class AppController {

	@FXML
	private AnchorPane anchorPane;
	@FXML
	private MenuController topMenuController;
	@FXML
	private SessionsController sessionsController;
	
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
	}
	
	public void addNewSession() throws IOException {
		sessionsController.addNewSession();
	}
	public void changeLanguageTo(String language) {
		System.out.println("I want to change language to: "+language);
	}
	
}