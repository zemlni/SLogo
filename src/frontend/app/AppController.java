package frontend.app;

import java.io.IOException;

import frontend.nonfxml.AppView;
import frontend.nonfxml.view.IViewController;
import language.LanguageSetter;

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
