package backend;

public class Variable implements VariableInterface {
	private double value;
	private String key;
	private String name;

	public Variable(String name, double value){
		this.name = name.toUpperCase();
		this.value = value;
		//may have to change when we are dealing with scope.
		this.key = name.toUpperCase();
	}
	@Override
	public String getKey() {
		return key;
	}

	@Override
	public void update(double newValue) {
		value = newValue;
	}

	@Override
	public double getValue() {
		return value;
	}

}
