package frontend.views;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;
/**@author matt nikita
 * */
public class DisplayController {
	private Map<Integer, Color> colors;
	private Map<Integer, File> turtleImages;
	private int penColor;
	private int shape;
	private TurtleScreenController turtleScreenController;

	public DisplayController(TurtleScreenController controller){
		turtleScreenController = controller;
		colors = new HashMap<Integer, Color>(){{
			put(0, Color.RED);
			put(1, Color.BLUE);
			put(2, Color.GREEN);
			put(3, Color.ORANGE);
			put(4, Color.BLACK);
			put(5, Color.WHITE);
			put(6, Color.YELLOW);
		}};
		
		turtleImages = new HashMap<Integer, File>(){{
			put(0, new File("turtle.png"));
			put(1, new File("turtle2.png"));
		}};
		
	}
	
	public void setPenSize(int width){
		turtleScreenController.setPenThickness(width);
	}
	
	/**params is a 4 element array. params[0] = index. params[1] = r. params[2] = g. params[3] = b
	 * */
	public void setPalette(int[] params){
		Color newColor = Color.rgb(params[1], params[2], params[3]);
		colors.put(params[0], newColor);
	}
	
	//Needs work
	public void setShape(int index){
		if(index >= 0 && index <= turtleImages.size()){
			turtleScreenController.setTurtleImage(turtleImages.get(index));
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
		}
	}
	
	//Needs work
	public int getPenColor(){
		return 0;
	}

	//Needs work
	public int getShape(){
		return shape;
	}
}
