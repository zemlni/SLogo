package frontend.nonfxml;

import frontend.app.SessionsController;
import frontend.nonfxml.view.IControllableView;
import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class SessionsView extends TabPane implements IControllableView {

	private SessionsController controller;
	
	public SessionsView() {
		this.setSide(Side.TOP);
		// TODO initial tabs
		Tab tab1 = new Tab("tab1");
		tab1.setContent(new FrontEndView());
		Tab tab2 = new Tab("tab2");
		tab2.setContent(new FrontEndView());
		this.getTabs().addAll(tab1, tab2);
		controller = new SessionsController(this);
	}
	
	@Override
	public SessionsController getController() {
		return controller;
	}

}
