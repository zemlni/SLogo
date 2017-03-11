package backend;

import java.util.ArrayList;
import java.util.List;

import backend.commands.MakeUserInstructionCommand;
import backend.commands.UserCommand;
import backend.parser.Expression;
import backend.parser.Input;

/**
 * @author nikita This class is the superclass for all commands. It contains
 *         methods that are common to all commands, in addition to methods
 *         required for parsing and setting up the command. An instance of this
 *         class is created when a blank command is identified in the parser
 *         (one that hasn't yet been defined but fits the command syntax)
 */
public class Command extends Expression implements CommandInterface, java.io.Serializable {

	private static final long serialVersionUID = -999830543243605085L;
	private int numArgs;
	private String name;

	public Command(Input info, BackendController controller) {
		this(info, controller, 0);
	}

	public String getKey() {
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

	/**
	 * evaluate this command.
	 * 
	 * @return a Variable instance containing the result of evaluating the
	 *         command
	 */
	public Variable evaluate() {
		if (isDefinedLangCommand(name))
			return new Variable(null, execute());
		else if (isDefinedUserCommand(name)) {
			try {
				UserCommand temp = (UserCommand) getBackendController().getParser().getCommandTable().getCommand(name);
				UserCommand command = new UserCommand(name, getBackendController(), getInfo(), temp.getArgNames(),
						temp.getCommands());
				command.addChildren(getChildren());
				command.setBackendController(getBackendController());
				command.getCommands().setBackendController(getBackendController());
				return new Variable(null, command.execute());
			} catch (CommandException e) {
				getBackendController().getParser().complain(e);
				return null;
			}
		} else if (getParent() instanceof MakeUserInstructionCommand)
			return new Variable(name, 0);
		getBackendController().getParser().complain(new CommandException(name));
		return null;
	}

	@Override
	public double execute() {
		return 0;
	}

	@Override
	public int getNumArgs() {
		return numArgs;
	}

	@Override
	public void setNumArgs(int numArgs) {
		this.numArgs = numArgs;
		this.setNumChildren(numArgs);
	}

	/**
	 * evaluates the arguments of this command, and returns the list of
	 * variables
	 * 
	 * @return list of variables resulting from evaluating the arguments of this
	 *         command
	 */
	@Override
	public List<Variable> getArgs() {
		List<Expression> children = getChildren();
		List<Variable> ret = new ArrayList<Variable>();
		for (Expression child : children)
			ret.add(child.evaluate());
		return ret;
	}
	
	@Override
	public String toString() {
		return "Command "+name;
	}
	
}
