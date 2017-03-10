package backend;


/**
 * @author nikita This is the implementation of the Variable. Instances of this
 *         class are created and passed around on numerous occasions. They
 *         represent, literally, variables with a name and with a value. They
 *         also may have info associated with them
 * 
 */
public class Variable implements VariableInterface, java.io.Serializable {

	private static final long serialVersionUID = 2157544570506055205L;

	private double value;
	private String key;

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

	@Override
	public void update(double newValue) {
		value = newValue;
	}

	@Override
	public double getValue() {
		return value;
	}
}
