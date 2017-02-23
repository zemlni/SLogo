package backend;

public class Variable implements VariableInterface {
	private double value;

	public Variable(double value) {
		this.value = value;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(double newValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getValue() {
		return value;
	}

}
