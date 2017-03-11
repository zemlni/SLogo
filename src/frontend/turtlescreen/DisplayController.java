package frontend.turtlescreen;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import frontend.turtlescreen.preferences.ColorNodePalette;
import frontend.turtlescreen.preferences.ImageNodePalette;
import frontend.turtlescreen.preferences.Palette;
import frontend.turtlescreen.preferences.PaletteEntry;
import frontend.turtlescreen.preferences.PaletteIndexAlert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import utils.javafx.FX;
/**@author matt, nikita, keping
 * */
public class DisplayController {
	private Map<Integer, Color> colors;
	private Map<Integer, File> turtleImages;

	private TurtleScreenController turtleScreenController;
	private Palette colorPalette;
	private Palette imagePalette;

	private static final int INIT_PEN_SIZE = 1;
	private static final int INIT_SHAPE_INDEX = 0;
	private static final int INIT_BACKGROUND_INDEX = 5;
	private static final int INIT_PEN_COLOR_INDEX = 0;
	private int penSize = INIT_PEN_SIZE; 
	private int shapeIndex = INIT_SHAPE_INDEX;
	private int backgroundIndex = INIT_BACKGROUND_INDEX;
	private int penColorIndex = INIT_PEN_COLOR_INDEX;
	

	public DisplayController(TurtleScreenController controller){
		initialize();
		turtleScreenController = controller;
		syncConfig();
	}
	public DisplayController(TurtleScreenController controller, DisplayConfig config) {
		initialize();
		turtleScreenController = controller;
		penSize = config.getPenSize();
		shapeIndex = config.getShapeIndex();
		backgroundIndex = config.getBackgroundIndex();
		penColorIndex = config.getPenColorIndex();
		syncConfig();
	}
	private void initialize() {
		// From Keping: I've changed here to not use double brace initialization
		// The syntax is unusual, and will perhaps cause trouble.
		// http://stackoverflow.com/questions/1958636/what-is-double-brace-initialization-in-java
		
		//default values
		colors = new HashMap<Integer, Color>();
		colors.put(0, Color.BLACK);
		colors.put(1, Color.BLUE);
		colors.put(2, Color.GREEN);
		colors.put(3, Color.ORANGE);
		colors.put(4, Color.RED);
		colors.put(5, Color.WHITE);
		colors.put(6, Color.YELLOW);	
		
		//default values
		turtleImages = new HashMap<Integer, File>();
		turtleImages.put(0, new File("turtle.png"));
		turtleImages.put(1, new File("turtle2.png"));
		
		ArrayList<PaletteEntry> entries = new ArrayList<PaletteEntry>();
		for(Integer index : colors.keySet()){
			entries.add(new PaletteEntry(new ColorNodePalette(colors.get(index)), index));
		}
		colorPalette = new Palette(entries);
		
		ArrayList<PaletteEntry> imageEntries = new ArrayList<PaletteEntry>();
		for(Integer index : turtleImages.keySet()){
			imageEntries.add(new PaletteEntry(new ImageNodePalette(turtleImages.get(index)), index));
		}
		imagePalette = new Palette(imageEntries);
	}
	private void syncConfig() {
		setPenSize(penSize);
		setShape(shapeIndex);
		setBackground(backgroundIndex);
		setPenColor(penColorIndex);
	}
	
	
	public void showColorPalette(){
		colorPalette.show();
	}
	
	public void setPenSize(int width){
		penSize = width;
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
		if(turtleImages.get(index) == null){
			new PaletteIndexAlert(AlertType.ERROR);
		}
		else{
			turtleScreenController.setTurtleImage(turtleImages.get(index));
			shapeIndex = index;
		}
	}
	
	public void setBackground(int index){
		if(colors.get(index) == null){
			new PaletteIndexAlert(AlertType.ERROR);
		}
		else{
			turtleScreenController.setBackground(colors.get(index));
			backgroundIndex = index;
		}
	}
	
	public void setPenColor(int index){
		if(colors.get(index) == null){
			new PaletteIndexAlert(AlertType.ERROR);
		}
		else{
			turtleScreenController.setPenColor(colors.get(index));
			penColorIndex = index;
		}
	}
	
	public int getPenColor(){
		return penColorIndex;
	}
	
	public int getShape(){
		return shapeIndex;
	}
	
	public DisplayConfig getConfig() {
		return new DisplayConfig(penSize, shapeIndex, backgroundIndex, penColorIndex);
	}
}
