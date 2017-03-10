package frontend.preferences;

import frontend.views.TurtleScreenController;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import utils.javafx.FX;

public class TurtleVisibleButtons extends HBox{
	
	public TurtleVisibleButtons(TurtleScreenController turtleScreenController){
		super();
		Button hide = FX.button("HideTurtle", e -> turtleScreenController.hideAllTurtles());
		Button show = FX.button("ShowTurtle", e -> turtleScreenController.showAllTurtles());
		getChildren().addAll(hide, show);
	}

}
