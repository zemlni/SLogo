package frontend.app;

import java.io.IOException;

import javafx.fxml.FXML;

public class AppController {

	@FXML
	private MenuController topMenuController;
	@FXML
	private SessionsController sessionsController;
	
	public AppController() {
		// A controller must have a public no-args constructor.
	}
	@FXML
	private void initialize() {
		topMenuController.setAppController(this);
		sessionsController.setAppController(this);
	}
	
	public void addNewSession() throws IOException {
		sessionsController.addNewSession();
	}
	
}
