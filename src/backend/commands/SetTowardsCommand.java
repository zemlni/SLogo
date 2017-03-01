package backend.commands;

import backend.BackendController;

public class SetTowardsCommand extends TurtleCommand{

	public SetTowardsCommand(BackendController controller) {
		super(controller, 2);
	}

	@Override
	public double execute() {
		double newDir = calculateTurn();
		rotateTurtle(newDir);
		return newDir - getTurtle().getDirection();
	}

	private void rotateTurtle(double newDir) {
		rotateTurtleModel(newDir);
		rotateTurtleView(newDir);
	}
	
	private void rotateTurtleModel(double newDir){
		getTurtle().setDir(newDir);
	}
	
	private void rotateTurtleView(double newDir){
		getTurtle().getFrontController().setTurtleAngle(newDir);
	}
	
	private double calculateTurn(){
		double currX = getTurtle().getXCoor();
		double currY = getTurtle().getYCoor();
		double towX = getArgs().get(0).getValue();
		double towY = getArgs().get(1).getValue();

		if(currX == towX && towY > currY){
			return 0;
		} else
		if(currX == towX && towY < currY){
			return 180;
		} else
		if(currY == towY && towX > currX){
			return 90;
		} else
		if(currY == towY && towX < currX){
			return 270;
		} else
		if(towX > currX && towY > currY){
			return 0 + Math.atan((currX-towX)/(currY-towY));
		} else
		if(towX > currX && towY < currY){
			return 180 - Math.atan((currX-towX)/(currY-towY));
		} else
		if(towX < currX && towY > currY){
			return 180 + Math.atan((currX-towX)/(currY-towY));
		} else
		if(towX < currX && towY < currY){
			return 360 - Math.atan((currX-towX)/(currY-towY));
		} else {
			return getTurtle().getDirection();
		}
	}

}
