package backend;

import java.util.ArrayList;
import java.util.List;

import backend.commands.UserCommand;
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
		super(info, controller, i);
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
		if (isDefinedLangCommand(name))
			return new Variable(null, execute());
		else if (isDefinedUserCommand(name)){
			try {//TODO: adds extra arguments in here 
				System.out.println("EXECUTING FROM COMMAND: " + name);
				for (Expression child: getChildren())
					System.out.println(child.getClass());
				//TODO: Problem - adding arguments to the same command
				UserCommand temp = (UserCommand)getBackendController().getParser().getCommandTable().getCommand(name);
				UserCommand command = new UserCommand(name, getBackendController(), getInfo(), temp.getArgNames(), temp.getCommands());
				command.addChildren(getChildren());
				return new Variable(null, command.execute());
			} catch (CommandException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		else
			return new Variable(name, 0);
	}

	@Override
	public double execute(){
		System.out.println("ERROR NEVER SHOULD HAVE RUN commands.Command");
		return 0;
	};

	@Override
	public int getNumArgs() {
		return numArgs;
	}

	@Override
	public void setNumArgs(int numArgs) {
		this.numArgs = numArgs;
		this.setNumChildren(numArgs);
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
			//System.out.println(child.getClass());
			ret.add(child.evaluate());
		}
		return ret; 
	}
}
