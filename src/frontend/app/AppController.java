package frontend.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import frontend.IViewController;
import frontend.frontend.FrontEndConfig;
import frontend.frontend.FrontEndView;
import frontend.menu.MenuController;
import frontend.sessions.SessionsController;
import javafx.stage.FileChooser;
import utils.FileChooserOption;
import utils.MyFileIO;
import utils.javafx.FX;
import utils.language.LanguageSetter;

public class AppController implements IViewController {
	private static final FileChooser.ExtensionFilter SLOGO_CONF_EXT = new FileChooser.ExtensionFilter("SLogo config file", "*.logo_conf");

	private MenuController menuController;
	private SessionsController sessionsController;
	private LanguageSetter languageSetter;

	public AppController(AppView view) {
		menuController = view.getMenuController();
		sessionsController = view.getSessionsController();

		menuController.setAppController(this);
		sessionsController.setAppController(this);
		languageSetter = new LanguageSetter();
	}

	public void addNewSession() {
		sessionsController.addNewSession();
	}

	public void openSession() {
		File file = MyFileIO.chooseFile(FileChooserOption.OPEN, SLOGO_CONF_EXT);
		if (file == null) { return; }
		FrontEndConfig frontEndConfig = null;
		try {
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			frontEndConfig = (FrontEndConfig) in.readObject();
			in.close();
			fileIn.close();
		} catch (Exception e) {
			//TODO
			e.printStackTrace();
			FX.alertError("ErrorTitle", "ConfigOpenError", file.getName());
		}
		if (frontEndConfig != null) {
			sessionsController.addSession(new FrontEndView(frontEndConfig));
		}
	}

	public void saveSession() {
		File file = MyFileIO.chooseFile(FileChooserOption.SAVE, SLOGO_CONF_EXT);
		if (file == null) { return; }
		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(sessionsController.getCurrentSession().getConfig());
			out.close();
			fileOut.close();
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
			FX.alertError("ErrorTitle", "ConfigSaveError", file.getName());
		}
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
