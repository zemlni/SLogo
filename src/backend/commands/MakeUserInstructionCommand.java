package backend.commands;

import java.util.ArrayList;
import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Expression;
import backend.parser.ListStartExpression;
import backend.parser.Input;

/**
 * @author nikita This is the implementation of the MakeUserInstruction command.
 *         An instance of this class gets created when the parser identifies
 *         that the user typed a MakeUserInstruction command
 */
public class MakeUserInstructionCommand extends Command {
	private String name;
	private Input in;

	public MakeUserInstructionCommand(Input in1, BackendController controller) {
		super(in1, controller, 2);
		in = new Input(in1.getInput(), in1.getIndex(), in1.getBreakPoints(), in1.getLineNumbers());
		in.incrementIndex();
		name = in.get();
		in.incrementIndex();
		ListStartExpression expr = new ListStartExpression(in, controller);
		in.decrementByCount();
		in.decrementIndex();
		int numArgs = expr.getChildren().size();
		Command newCommand = new Command(in, controller);
		newCommand.setNumArgs(numArgs);
		getBackendController().getParser().getCommandTable().setCommand(newCommand);
		in1.incrementIndex();
	}

	/**
	 * Makes the user defined command. The name given in the first argument is
	 * assigned the variables given in the list in the second argument and the
	 * commands given in the list in the third argument.
	 * 
	 * @return 1 if the command was created successfully, else 0
	 */
	@Override
	public double execute() {
		List<Variable> args = new ArrayList<Variable>();
		for (int i = 0; i < getChildren().get(0).getChildren().size(); i++) {
			Variable var = getChildren().get(0).getChildren().get(i).evaluate();
			args.add(var);
		}
		Expression commands = getChildren().get(1);
		UserCommand newCommand = new UserCommand(name, getBackendController(), in, args, commands);
		getBackendController().getParser().getCommandTable().setCommand(newCommand);
		return 0;
	}

}
