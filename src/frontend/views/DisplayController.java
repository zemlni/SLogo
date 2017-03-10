package frontend.views;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
/**@author matt nikita
 * */
public class DisplayController {
	private Map<Integer, Color> colors;
	private int penColor;
	private int shape;

	public DisplayController(){
		colors = new HashMap<Integer, Color>(){{
			put(0, Color.RED);
			put(1, Color.BLUE);
			put(2, Color.GREEN);
			put(3, Color.ORANGE);
			put(4, Color.BLACK);
			put(5, Color.WHITE);
			put(6, Color.YELLOW);
		}};
	}
	public void setPenSize(int index){}
	/**params is a 4 element array. params[0] = index. params[1] = r. params[2] = g. params[3] = b
	 * */
	public void setPalette(int[] params){}
	public void setShape(int index){}
	public void setBackground(int index){}
	public void setPenColor(int index){}
	public int getPenColor(){
		return penColor;
	}
	public int getShape(){
		return shape;
	}
}
