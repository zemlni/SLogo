package frontend.preferences;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColorNodePalette extends Rectangle {
	
	public static final double RECTANGLE_SIDE = 30;
	
	public ColorNodePalette(Color color){
		super(RECTANGLE_SIDE, RECTANGLE_SIDE);
		setFill(color);
	}
}
