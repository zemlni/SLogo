package frontend.turtles;

import javafx.scene.control.Slider;

public class PenWidthSlider extends Slider {

	public PenWidthSlider(){
		super();
		setMin(0);
		setMax(30);
		setValue(10);
		setShowTickLabels(true);
		setShowTickMarks(true);
		setMajorTickUnit(10);
		setMinorTickCount(5);
		setBlockIncrement(1);
	}
}
