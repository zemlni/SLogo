package frontend.app;

import java.io.IOException;

import frontend.help.BrowserWindow;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

public class MenuController {

	@FXML
	private ComboBox<String> languageBox;
	private AppController appController;

	public void setAppController(AppController appController) {
		this.appController = appController;
	}

	@FXML
	private void initialize() {
		initLanguageBox();
	}

	private void initLanguageBox() {
		languageBox.getItems().addAll(
				"English",
				"中文"
			);
		languageBox.setValue("English");
		languageBox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String ov, String nv) {
				if (!ov.equals(nv)) {
					changeLanguageTo(nv);
				}
			}
		});
	}
	

	@FXML
	private void addNewSession() throws IOException {
		appController.addNewSession();
	}

	@FXML
	private void openHelpPage() {
		try {
			BrowserWindow helpPage = new BrowserWindow("http://kepingwang.com");
			helpPage.show();
		} catch (IOException e) {
			System.out.println("Sorry Cannot show help page :(");
		}
	}
	
	private void changeLanguageTo(String language) {
		appController.changeLanguageTo(language);
	}
	
	

}
