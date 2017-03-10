package backend;

public class Variable implements VariableInterface, java.io.Serializable{
	private double value;
	private String key;
	private String info;

	public Variable(String info){
		this(null, 0);
		this.info = info;
	}
	public Variable(String name, double value){
		if (name != null){
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
	}
	@Override
	public void update(double newValue) {
		value = newValue;
	}

	@Override
	public double getValue() {
		return value;
	}
	public String getInfo(){
		return info;
	}
}
