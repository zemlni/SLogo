package frontend.app;

import java.io.IOException;

import frontend.help.BrowserWindow;
import frontend.nonfxml.MenuView;
import frontend.nonfxml.view.IViewController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;

public class MenuController implements IViewController {

	private ComboBox<String> languageBox;
	private AppController appController;

	
	public MenuController(MenuView view) {
		languageBox = view.getLanguageBox();
		initLanguageBox();
	}

	public void setAppController(AppController appController) {
		this.appController = appController;
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
	

	public void addNewSession() {
		try {
			appController.addNewSession();
		} catch (IOException e) { } // TODO
	}

	public void openSession() {
		// TODO
	}
	public void saveSession() {
		// TODO
	}

	public void openHelpPage() {
		try {
			BrowserWindow helpPage = new BrowserWindow("http://www.cs.duke.edu/courses/compsci308/spring17/assign/03_slogo/commands.php");
			helpPage.show();
		} catch (IOException e) {
			System.out.println("Sorry Cannot show help page :(");
		}
	}
	
	private void changeLanguageTo(String language) {
		appController.changeLanguageTo(language);
	}
	
	

}
