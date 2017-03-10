package frontend.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import frontend.nonfxml.AppView;
import frontend.nonfxml.FrontEndView;
import frontend.nonfxml.IViewController;
import frontend.nonfxml.config.FrontEndConfig;
import language.LanguageSetter;
import utils.FileChooserOption;
import utils.MyFileIO;

public class AppController implements IViewController {

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
		File file = MyFileIO.chooseFile(FileChooserOption.OPEN);
		FrontEndConfig frontEndConfig = null;
		try {
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			frontEndConfig = (FrontEndConfig) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
		if (frontEndConfig != null) {
			sessionsController.addSession(new FrontEndView(frontEndConfig));
		}
	}

	public void saveSession() {
		File file = MyFileIO.chooseFile(FileChooserOption.SAVE);
		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(sessionsController.getCurrentSession().getConfig());
			out.close();
			fileOut.close();
			System.out.printf("Serialized data saved");
		} catch (Exception e) {
			e.printStackTrace();
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
