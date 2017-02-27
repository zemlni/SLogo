package frontend;

import frontend.views.TurtleImage;

public class TurtleController implements TurtleControllerInterface {
	private TurtleImage turtle;
	
	public TurtleController(){
		turtle = new TurtleImage();
	}

	@Override
	public void moveTurtleTo(double x, double y) {
		
	}

	@Override
	public void drawLine(double x0, double y0, double x1, double y1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTurtleAngle(double angle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearScreen() {
		// TODO Auto-generated method stub
		
	}

}
