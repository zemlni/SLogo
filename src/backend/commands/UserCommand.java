package backend.commands;

import java.util.ArrayList;
import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.UserCommandInterface;
import backend.Variable;
import backend.parser.Expression;
import backend.parser.Input;

public class UserCommand extends Command implements UserCommandInterface {
	private String name;
	private List<Variable> argNames;
	private Expression commands;

	public UserCommand(String name, BackendController controller, Input in, List<Variable> argNames,
			Expression commands) {
		super(in, controller, argNames.size());
		this.name = name;
		this.argNames = argNames;
		this.commands = commands;
	}

	/**
	 * execute this user defined command
	 * 
	 * @return the return value of the last executed command.
	 */
	@Override
	public double execute() {
		// for (Expression child: getChildren())
		// System.out.println(child.getClass());
		List<Double> old = new ArrayList<Double>();
		for (int i = 0; i < argNames.size(); i++) {
			String varName = argNames.get(i).getKey();
			System.out.println("VARNAME: " + varName);
			double newVal = getChildren().get(i).evaluate().getValue();
			System.out.println("Setting new var: " + newVal);
			old.add(newVal);
			getBackendController().setVariable(new Variable(varName, newVal));
		}
		double ret = commands.evaluate().getValue();
		for (int i = 0; i < old.size(); i++){
			String varName = argNames.get(i).getKey();
			double newVal = old.get(i);
			getBackendController().getParser().getVariableTable().removeVariable(new Variable(varName, newVal));
			System.out.println("REMOVING Old var: " + varName + " " + newVal);
		}
		return ret;// getChildren().get(0).evaluate().getValue();
	}

	@Override
	public String getKey() {
		return name;
	}

	public List<Variable> getArgNames() {
		return argNames;
	}

	public Expression getCommands() {
		return commands;
	}

}
