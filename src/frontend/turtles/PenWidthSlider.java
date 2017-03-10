package frontend.turtles;

import frontend.views.TurtleScreenController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;

public class PenWidthSlider extends Slider {

	public PenWidthSlider(TurtleScreenController turtleScreenController){
		super();
		setMin(0);
		setMax(30);
		setValue(turtleScreenController.getPenWidth());
		setShowTickLabels(true);
		setShowTickMarks(true);
		setMajorTickUnit(10);
		setMinorTickCount(5);
		setBlockIncrement(1);
		
		valueProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				turtleScreenController.setPenThickness(newValue.doubleValue());
			}
		});
	}
}
