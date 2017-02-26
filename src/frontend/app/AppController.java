package frontend.app;

import javafx.fxml.FXML;

public class AppController {

	@FXML
	private MenuController topMenuController;
	
	public AppController() {
		// A controller must have a public no-args constructor.
	}
	@FXML
	private void initialize() {

	}
	
	public void callPrint() {
		topMenuController.print();
	}
	
}
