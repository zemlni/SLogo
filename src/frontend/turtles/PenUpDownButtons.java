package frontend.turtles;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import language.Language;
import utils.javafx.FX;

public class PenUpDownButtons extends HBox {

	public PenUpDownButtons(){
		super();
		Button penUp = new Button("Pen Up");
		Button penDown = new Button("Pen Down");
		getChildren().addAll(penUp, penDown);
	}
	
}
