package utils.javafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import utils.language.Language;

/**
 * Class with static methods to help build JavaFX components.
 * @author keping
 *
 */
public class FX {

	private FX() { };
	
	/**
	 * Defines the four anchors of the child: top, right, bottom, left. 
	 */
	public static void setAnchors(Node child, double top, double right, double bottom, double left) {
		AnchorPane.setTopAnchor(child, top);
		AnchorPane.setRightAnchor(child, right);
		AnchorPane.setBottomAnchor(child, bottom);
		AnchorPane.setLeftAnchor(child, left);
	}
	
	/**
	 * Returns a Button with text binding {@code textKey}, and an action handler.
	 */
	public static Button button(String textKey, EventHandler<ActionEvent> handler) {
		Button button = new Button();
		button.textProperty().bind(Language.createStringBinding(textKey));
		button.setOnAction(handler);
		return button;
	}
	
	/**
	 * Returns a TitledPane with text binding {@code textKey}, and the content node. 
	 */
	public static TitledPane titledPane(String textKey, Node content) {
		TitledPane titledPane = new TitledPane();
		titledPane.textProperty().bind(Language.createStringBinding(textKey));
		titledPane.setContent(content);
		return titledPane;
	}
	
	/**
	 * Return a Tab with raw string {@code textRaw} as title, 
	 * and {@code content} as the content.
	 */
	public static Tab tabRaw(String textRaw, Node content) {
		Tab tab = new Tab(textRaw);
		tab.setContent(content);
		return tab;
	}
	
	/**
	 * Returns a Tab with text binding {@code textKey}, and content. 
	 */
	public static Tab tab(String textKey, Node content) {
		Tab tab = new Tab();
		tab.textProperty().bind(Language.createStringBinding(textKey));
		tab.setContent(content);
		return tab;
	}
	
	public static void alertError(String errorTitleKey, String errorBodyKey, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(Language.getWord(errorTitleKey));
		alert.setContentText(Language.getWord(errorBodyKey) + content);
		alert.showAndWait();
	}
	
}
