package backend;

import java.util.List;

public abstract class Command implements CommandInterface {

	private int numArgs;
	private List<Double> args;

	@Override
	public abstract double execute();

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumArgs() {
		return numArgs;
	}

	protected void setNumArgs(int numArgs) {
		this.numArgs = numArgs;
	}

	public void setArgs(List<Double> vars) {
		this.args = vars;
	}

	public List<Double> getArgs() {
		return args;
	}
}
