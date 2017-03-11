package frontend.turtlescreen;

import frontend.IViewConfig;

public class DisplayConfig implements IViewConfig {

	private static final long serialVersionUID = 4933474332365616323L;
	
	private int penSize;
	private int shapeIndex;
	private int backgroundIndex;
	private int penColorIndex;
	
	public DisplayConfig(int penSize, int shapeIndex, int backgroundIndex, int penColorIndex) {
		this.penSize = penSize;
		this.shapeIndex = shapeIndex;
		this.backgroundIndex = backgroundIndex;
		this.penColorIndex = penColorIndex;
	}

	public int getPenSize() { return penSize; }
	public int getShapeIndex() { return shapeIndex; }
	public int getBackgroundIndex() { return backgroundIndex; }
	public int getPenColorIndex() { return penColorIndex; }
	
}
