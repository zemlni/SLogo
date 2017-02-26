package backend;

import java.util.List;

public abstract class Command implements CommandInterface {

	private int numArgs;
	private List<Variable> args;
	private Parser parser;

	public Command(Parser parser){
		this.parser = parser;
	}

	@Override
	public abstract double execute();

	@Override
	public int getNumArgs() {
		return numArgs;
	}
	@Override
	public void setNumArgs(int numArgs) {
		this.numArgs = numArgs;
	}
	@Override
	public void setArgs(List<Variable> vars) {
		this.args = vars;
	}
	@Override
	public List<Variable> getArgs() {
		return args;
	}
}
