package slogo_team08;

import frontend.FrontEndController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main (String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FrontEndController front = new FrontEndController(primaryStage);
		//BackEndController back = new BackEndController();
	}

}
