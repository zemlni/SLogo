package frontend.preferences;

import frontend.views.TurtleScreenController;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import language.Language;

public abstract class PreferenceTab extends Tab{
	private TurtleScreenController turtleScreenController;
	private VBox tabRoot;

	public PreferenceTab(TurtleScreenController controller, String titleKey){
		super();
		turtleScreenController = controller;
	
		this.textProperty().bind(Language.createStringBinding(titleKey));
		tabRoot = new VBox(10);
		setContent(tabRoot);
		addButtons();
	}
	
	public abstract void addButtons();
	
	public VBox getRoot(){
		return tabRoot;
	}
	
	public TurtleScreenController getController(){
		return turtleScreenController;
	}
}
