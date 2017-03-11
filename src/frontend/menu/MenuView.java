package frontend.menu;

import frontend.IControllableView;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import utils.javafx.FX;

public class MenuView extends HBox implements IControllableView {

	
	private ComboBox<String> languageBox;
	private MenuController controller;
	
	public MenuView() {
		languageBox = new ComboBox<String>();
		controller = new MenuController(this);
		
		Button newBtn = FX.button("New", e -> controller.addNewSession());
		Button openBtn = FX.button("Open", e -> controller.openSession());
		Button saveBtn = FX.button("Save", e -> controller.saveSession());
		Button helpBtn = FX.button("Help", e -> controller.openHelpPage());
		
		this.getChildren().addAll(newBtn, openBtn, saveBtn, helpBtn, languageBox);
	}
	
	public ComboBox<String> getLanguageBox() {
		return languageBox;
	}
	
	@Override
	public MenuController getController() {
		return controller;
	}

}
