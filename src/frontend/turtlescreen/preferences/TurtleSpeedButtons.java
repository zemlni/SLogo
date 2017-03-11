package frontend.turtlescreen.preferences;

import frontend.turtlescreen.TurtleScreenController;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import utils.javafx.FX;

public class TurtleSpeedButtons extends HBox {
	
	public TurtleSpeedButtons(TurtleScreenController turtleScreenController){
		super();
		Button slow = FX.button("SlowDown", e -> turtleScreenController.slowDown());
		Button speed = FX.button("SpeedUp", e -> turtleScreenController.speedUp());
		getChildren().addAll(slow, speed);
	}
}
