package frontend.turtles;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import language.Language;

public class ColorSelector extends HBox {
	private ColorPicker colorPicker;
	public static final int MAX_WIDTH = 40;
	public static final int MAX_HEIGHT = 40;
	private Font font = new Font(12);

	public ColorSelector (String titleKey){
		colorPicker = new ColorPicker();
		colorPicker.setMaxWidth(MAX_WIDTH);
		colorPicker.setMaxHeight(MAX_HEIGHT);
		colorPicker.setValue(Color.BLACK);
		Label pickerTitle = new Label();
		pickerTitle.textProperty().bind(Language.createStringBinding(titleKey));
		pickerTitle.setFont(font);
		getChildren().addAll(pickerTitle, colorPicker);
	}
	
	
	public ColorPicker getColorPicker(){
		return colorPicker;
	}
	
	public void setFont(Font font){
		this.font = font;
	}
}
