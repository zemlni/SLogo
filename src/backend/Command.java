package backend;

import java.util.ArrayList;
import java.util.List;

import backend.parser.Expression;
import backend.parser.Input;

public class Command extends Expression implements CommandInterface {

	private int numArgs;
	private List<Variable> args;
	private String name;

	public Command(Input info, BackendController controller) {
		this(info, controller, 0);
	}
	
	public String getKey(){
		return name;
	}

	public Command(Input info, BackendController controller, int i) {
		super(null, controller, i);
		this.numArgs = i;
		this.name = info.get();
	}

	private boolean isDefinedLangCommand(String name) {
		try {
			getBackendController().getParser().getCommandSymbol(name);
			return true;
		} catch (CommandException e) {
			return false;
		}
	}

	private boolean isDefinedUserCommand(String name) {
		try {
			getBackendController().getParser().getCommandTable().getCommand(name);
			return true;
		} catch (CommandException e) {
			return false;
		}
	}

	public boolean isDefinedCommand(String name) {
		return isDefinedUserCommand(name) || isDefinedLangCommand(name);
	}

	public Variable evaluate() {
		/*List<Variable> args = new ArrayList<Variable>();
		for (Expression child: getChildren())
			args.add(child.evaluate());
		setArgs(args);*/
		if (isDefinedCommand(getString()))
			return new Variable(null, execute());
		else
			return new Variable(getString(), 0);
	}

	@Override
	public double execute(){
		return 0;
	};

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
		List<Expression> children = getChildren();
		List<Variable> ret = new ArrayList<Variable>();
		for(Expression child: children){
			ret.add(child.evaluate());
		}
		return ret; 
	}
}
