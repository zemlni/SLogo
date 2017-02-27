package frontend.views;

import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class ColorSelector {
	private ColorPicker colorPicker;
	private Font titleFont;
	private HBox selectorPanel;
	public static final int MAX_WIDTH = 10;
	public static final int MAX_HEIGHT = 10;

	public ColorSelector(String title){
		colorPicker = new ColorPicker();
		colorPicker.setMaxWidth(MAX_WIDTH);
		colorPicker.setMaxHeight(MAX_HEIGHT);
		colorPicker.setValue(Color.BLACK);
		Text pickerTitle = new Text(title);
		titleFont = new Font(12);
		pickerTitle.setFont(titleFont);
		selectorPanel = new HBox();
		selectorPanel.getChildren().addAll(pickerTitle, colorPicker);
	}
	
	public HBox getPanel(){
		return selectorPanel;
	}
	
	public ColorPicker getColorPicker(){
		return colorPicker;
	}
	
}
