package frontend.views;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import frontend.preferences.ColorNodePalette;
import frontend.preferences.Palette;
import frontend.preferences.PaletteEntry;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import utils.javafx.FX;
/**@author matt nikita
 * */
public class DisplayController {
	private Map<Integer, Color> colors;
	private Map<Integer, File> turtleImages;
	private int penColor = 0;
	private int shape = 0;
	private TurtleScreenController turtleScreenController;
	private Palette colorPalette;



	public DisplayController(TurtleScreenController controller){
		turtleScreenController = controller;
		//default values
		colors = new HashMap<Integer, Color>(){{
			put(0, Color.BLACK);
			put(1, Color.BLUE);
			put(2, Color.GREEN);
			put(3, Color.ORANGE);
			put(4, Color.RED);
			put(5, Color.WHITE);
			put(6, Color.YELLOW);
		}};
		
		turtleImages = new HashMap<Integer, File>(){{
			put(0, new File("turtle.png"));
			put(1, new File("turtle2.png"));
		}};
		
		
		ArrayList<PaletteEntry> entries = new ArrayList<PaletteEntry>();
		for(Integer index : colors.keySet()){
			entries.add(new PaletteEntry(new ColorNodePalette(colors.get(index)), index));
		}
		colorPalette = new Palette(entries);
		
		ArrayList<PaletteEntry> entries2 = new ArrayList<PaletteEntry>();
		for(Integer index : turtleImages.keySet()){
			Image turtleImage = new Image(turtleImages.get(index).toURI().toString());
			ImageView turtle = new ImageView();
			turtle.setImage(turtleImage);
			entries2.add(new PaletteEntry(turtle, index));
		}
		
	}
	
	public Palette getPal(){
		return colorPalette;
	}
	
	public void setPenSize(int width){
		turtleScreenController.setPenThickness(width);
	}
	
	/**params is a 4 element array. params[0] = index. params[1] = r. params[2] = g. params[3] = b
	 * */
	public void setPalette(int[] params){
		if(params[1] < 0 || params[1] > 255 || params[2] < 0 || params[2] > 255 || params[3] < 0 || params[3] > 255){
			FX.alertError("BadRGB", "BadRGBMessage", "");
		}
		else{
			Color newColor = Color.rgb(params[1], params[2], params[3]);
			colorPalette.add(new PaletteEntry(new ColorNodePalette(newColor), params[0]));
			colors.put(params[0], newColor);
		}
	}
	
	
	public void setShape(int index){
		if(index >= 0 && index <= turtleImages.size()){
			turtleScreenController.setTurtleImage(turtleImages.get(index));
			shape = index;
		}
	}
	
	public void setBackground(int index){
		if(index >= 0 && index <= colors.size()){
			turtleScreenController.setBackground(colors.get(index));
			
		}
	}
	
	public void setPenColor(int index){
		if(index >= 0 && index <= colors.size()){
			turtleScreenController.setPenColor(colors.get(index));
			penColor = index;
		}
	}
	
	public int getPenColor(){
		return penColor;
	}

	//Needs work
	public int getShape(){
		return shape;
	}
}
