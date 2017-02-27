package backend;

public class Variable implements VariableInterface {
	private double value;
	private String key;
	private String name;

	public Variable(String name, double value){
		if (name != null){
			this.name = name.toUpperCase();
			this.key = name.toUpperCase();
		}
		this.value = value;
		
		//may have to change when we are dealing with scope.
			
	}
	@Override
	public String getKey() {
		return key;
	}

	public void setKey(String key){
		this.key = key;
		this.name = key;
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
