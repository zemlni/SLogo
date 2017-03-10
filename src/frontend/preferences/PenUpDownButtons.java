package frontend.preferences;

import frontend.views.TurtleScreenController;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import utils.javafx.FX;

public class PenUpDownButtons extends HBox {

	public PenUpDownButtons(TurtleScreenController turtleScreenController){
		super();
		Button penUp = FX.button("PenUp", e -> turtleScreenController.allPensUp());
		Button penDown = FX.button("PenDown", e -> turtleScreenController.allPensDown());
		getChildren().addAll(penUp, penDown);
	}
	
}
