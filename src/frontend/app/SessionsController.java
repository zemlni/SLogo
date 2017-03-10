package frontend.app;

import frontend.nonfxml.FrontEndView;
import frontend.nonfxml.IViewController;
import frontend.nonfxml.SessionsView;

public class SessionsController implements IViewController {
	public static final String MAIN_PAGE_RESOURCE = "/frontend/app/app.fxml";
	
	private SessionsView view;
	private AppController appController;
	
	public SessionsController(SessionsView view) {
		this.view = view;
	}
	
	public void setAppController(AppController appController) {
		this.appController = appController;
	}

	public FrontEndView getCurrentSession() {
		return (FrontEndView) view.getSelectedTab().getContent();
	}
	public void addNewSession() {
		view.addTab();
		view.selectLastTab();
	}
	public void addSession(FrontEndView frontEndView) {
		view.addTab(frontEndView);
		view.selectLastTab();
	}
	
	
}
