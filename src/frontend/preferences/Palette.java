package frontend.preferences;

import java.util.List;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Palette {
	public static final double PALETTE_WIDTH = 250;
	public static final double PALETTE_HEIGHT = 75;
	private HBox root;
	private Stage paletteStage;

	public Palette(List<PaletteEntry> entries){
		paletteStage = new Stage();
		root = new HBox();
		root.maxWidth(PALETTE_WIDTH);
		Scene pal = new Scene(root, PALETTE_WIDTH, PALETTE_HEIGHT);
		paletteStage.setScene(pal);
		
		for(PaletteEntry entry : entries){
			root.getChildren().add(entry);
		}
	}
	
	public void show(){
		paletteStage.show();
	}
	
	public void hide(){
		paletteStage.hide();
	}
	
	public void add(PaletteEntry entry){
		root.getChildren().remove(entry);
		if(entry.getIndex() <= root.getChildren().size()){
			root.getChildren().add(entry.getIndex(), entry);
		}
		else{
			root.getChildren().add(entry);
		}
	}
}
