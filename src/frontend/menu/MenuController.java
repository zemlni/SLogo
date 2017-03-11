package frontend.menu;

import java.io.IOException;
import frontend.IViewController;
import frontend.app.AppController;
import frontend.help.BrowserWindow;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import utils.javafx.FX;

public class MenuController implements IViewController {
	private static final String HELP_PAGE_URL = "http://www.cs.duke.edu/courses/compsci308/spring17/assign/03_slogo/commands.php";

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
		languageBox.getItems().addAll("English", "中文");
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
		appController.addNewSession();
	}
	public void openSession() {
		appController.openSession();
	}
	public void saveSession() {
		appController.saveSession();
	}
	public void openHelpPage() {
		String url = HELP_PAGE_URL;
		try {
			BrowserWindow helpPage = new BrowserWindow(url);
			helpPage.show();
		} catch (IOException e) {
			FX.alertError("ErrorTitle", "CannotShowHelpPage", url);
		}
	}

	private void changeLanguageTo(String language) {
		appController.changeLanguageTo(language);
	}

}
