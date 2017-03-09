package frontend.nonfxml;

import frontend.app.MenuController;
import frontend.nonfxml.view.IControllableView;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import language.Language;

public class MenuView extends HBox implements IControllableView {

	
	private ComboBox<String> languageBox;
	private MenuController controller;
	
	public MenuView() {
		languageBox = new ComboBox<String>();
		controller = new MenuController(this);
		
		Button newBtn = new Button();
		newBtn.textProperty().bind(Language.createStringBinding("New"));
		newBtn.setOnAction(e -> controller.addNewSession());
		Button openBtn = new Button();
		openBtn.textProperty().bind(Language.createStringBinding("Open"));
		openBtn.setOnAction(e -> controller.openSession());
		Button saveBtn = new Button();
		saveBtn.textProperty().bind(Language.createStringBinding("Save"));
		saveBtn.setOnAction(e -> controller.saveSession());
		Button helpBtn = new Button();
		helpBtn.textProperty().bind(Language.createStringBinding("Help"));
		helpBtn.setOnAction(e -> controller.openHelpPage());
		
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
