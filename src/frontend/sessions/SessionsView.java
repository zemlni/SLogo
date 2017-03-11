package frontend.sessions;

import frontend.IControllableView;
import frontend.frontend.FrontEndView;
import javafx.geometry.Side;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import utils.javafx.FX;

public class SessionsView extends TabPane implements IControllableView {

	private static final int INIT_TABS = 2;
	private int tabCounter = 1;
	private SessionsController controller;
	
	public SessionsView() {
		this.setSide(Side.TOP);
		for (int i = 0; i < INIT_TABS; i++) {
			addTab();
		}
		controller = new SessionsController(this);
	}
	
	private int tabCount() {
		return tabCounter++;
	}
	
	public void addTab() {
		Tab tab = FX.tabRaw("tab"+tabCount(), new FrontEndView());
		this.getTabs().add(tab);
	}
	public void addTab(FrontEndView frontEndView) {
		Tab tab = FX.tabRaw("tab"+tabCount(), frontEndView);
		this.getTabs().add(tab);	
	}
	private int numTabs() {
		return this.getTabs().size();
	}
	public Tab getSelectedTab() {
		return this.getSelectionModel().getSelectedItem();
	}
	public void selectLastTab() {
		SingleSelectionModel<Tab> selectionModel = this.getSelectionModel();
		selectionModel.select(numTabs() - 1);
	}
	
	@Override
	public SessionsController getController() {
		return controller;
	}

}
