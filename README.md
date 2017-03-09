# slogo

A development environment that helps users write SLogo programs.


### Suggestions (from Keping) for refactoring turtle related commands:

It is probably cleaner to put all turtle related in a separate package. 

It's good that we already have a `TurtleModel` in backend controller. We just have to move as many code as possible from each `Command` to this `TurtleModel`. Currently `TurtleModel` really does nothing except for storing variables. It really wants to "do something". The point of object oriented programming is more than just storing data together (we can use maps and lists to store data). Tell the turtle what to do. Instead of doing it yourself in each command. In this way we can easily modify `TurtleModel` to allow multiple turtles in the backend.

For example, this is our current HideTurtleCommand:

```
public class HideTurtleCommand extends TurtleCommand{
	public HideTurtleCommand(Input in, BackendController controller) {
		super(in, controller, 0);
	}
	@Override
	public double execute() {
		updateTurtleVisibility();
		return 0;
	}
	private void updateTurtleVisibility() {
		updateTurtleModel();
		updateTurtleView();
	}
	private void updateTurtleView() {
		getTurtle().getFrontController().hideTurtle();
	}
	private void updateTurtleModel(){
		getTurtle().setInvis();
	}
}

```

It will feel much better if it looks like:
```
public class HideTurtleCommand extends TurtleCommand{
	public HideTurtleCommand(Input in, BackendController controller) {
		super(in, controller, 0);
	}
	@Override
	public double execute() {
		getTurtle().hide();
		return 0;
	}
}

```

Since we want the backend and frontend to be syncronized, it is better to put the backend update and frontend update as close as possible, just so that we can never call the backend to update without updating the frontend. So the `TurtleModel` could have a method like:
```
public class TurtleModel {
    ...
    public void hide() {
        getFrontController().hideTurtle();
        setInvis();
    }
    ...
}
```

Another example, currently within our `Forward Command`:
```
	@Override
	public double execute() {
		double forwardAmount = 0;
		for (Variable var : getArgs()) {
			forwardAmount = var.getValue();
			moveTurtle(forwardAmount);
		}
		return forwardAmount;
	}

	/*
	 * moves the turtle in the model, and in the display of the turtle
	 */
	private void moveTurtle(double traveled) {
		TurtleModel turtle = getTurtle();
		double oldXCoor = turtle.getXCoor();
		double oldYCoor = turtle.getYCoor();
		double turtleDirection = turtle.getDirection();

		double newXCoor = calcNewXCoor(turtleDirection, oldXCoor, traveled);
		double newYCoor = calcNewYCoor(turtleDirection, oldYCoor, traveled);

		updateTurtleModel(newXCoor, newYCoor, turtle);
		updateTurtleView(oldXCoor, oldYCoor, newXCoor, newYCoor, turtle);
	}
```

It could be:
```
	@Override
	public double execute() {
		double forwardAmount = 0;
		for (Variable var : getArgs()) {
			forwardAmount = var.getValue();
			getTurtle.moveForward(forwardAmount);
		}
		return forwardAmount;
	}
```


