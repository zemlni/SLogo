package frontend.app;

import frontend.IControllableView;
import frontend.menu.MenuController;
import frontend.menu.MenuView;
import frontend.sessions.SessionsController;
import frontend.sessions.SessionsView;
import javafx.scene.layout.AnchorPane;
import utils.javafx.FX;

public class AppView extends AnchorPane implements IControllableView {
	
	private MenuView menuView;
	private SessionsView sessionsView;
	private AppController controller;
	
	public AppView() {
		menuView = new MenuView();
		sessionsView = new SessionsView();
		
		this.getChildren().addAll(menuView, sessionsView);
		FX.setAnchors(sessionsView, 37.0, 0.0, 0.0, 0.0);

		controller = new AppController(this);
	}

	public MenuController getMenuController() {
		return menuView.getController();
	}
	public SessionsController getSessionsController() {
		return sessionsView.getController();
	}
	
	@Override
	public AppController getController() {
		return controller;
	}

}
