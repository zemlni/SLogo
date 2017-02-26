package frontend.app;

import java.io.IOException;

import javafx.fxml.FXML;

public class MenuController {
	
	private AppController appController;
	
	public void setAppController(AppController appController) {
		this.appController = appController;
	}
	
	@FXML
	private void addNewSession() throws IOException {
		appController.addNewSession();
	}
	
	
}
