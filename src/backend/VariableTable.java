package backend;

import java.util.HashMap;

public class VariableTable implements VariableTableInterface {
	
	private HashMap<String, Variable> variables;
	
	public VariableTable(){
		variables = new HashMap<String, Variable>();
		setVariable("turtleLocationX", 0);
		setVariable("turtleLocationY", 0);
		setVariable("turtleAngle", 0);
	}
	@Override
	public Variable getVariable(String name) throws CommandError{
		Variable ret = variables.get(name.toUpperCase());
		if (ret == null)
			throw new CommandError();
		return ret;
	}

	@Override
	public void setVariable(String name, double value) {
		variables.remove(name.toUpperCase());
		variables.put(name.toUpperCase(), new Variable(name, value));

	}

	@Override
	public void removeVariable(String name) {
		variables.remove(name.toUpperCase());

	}

}
