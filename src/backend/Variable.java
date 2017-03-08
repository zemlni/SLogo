package backend;

/**
 * @author nikita This is the implementation of the Variable. Instances of this
 *         class are created and passed around on numerous occasions. They
 *         represent, literally, variables with a name and with a value. They
 *         also may have info associated with them
 * 
 */
public class Variable implements VariableInterface {
	private double value;
	private String key;
	private String info;

	public Variable(String info) {
		this(null, 0);
		this.info = info;
	}

	public Variable(String name, double value) {
		if (name != null) {
			this.key = name.toUpperCase();
		}
		this.value = value;
	}

	@Override
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
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

	public String getInfo() {
		return info;
	}
}
