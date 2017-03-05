package backend.commands;

import java.util.ArrayList;
import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Expression;
import backend.parser.ListStartExpression;
import backend.parser.VariableExpression;
import backend.parser.Input;

public class MakeUserInstructionCommand extends Command {
	private String name;
	private Input in;

	public MakeUserInstructionCommand(Input in1, BackendController controller) {
		super(in1, controller, 2);
		in = new Input(in1.getInput(), in1.getIndex(), in1.getBreakPoints());
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

	@Override
	public double execute() {
		List<Variable> args = new ArrayList<Variable>();
		for (int i = 0; i < getChildren().get(0).getChildren().size(); i++) {
			Variable var = getChildren().get(0).getChildren().get(i).evaluate();
			args.add(var);
		}
		List<Expression> vals = null;
		try {
			vals = getBackendController().getParser().getCommandTable().getCommand(name).getChildren();
		} catch (Exception e) {
			// TODO do something else
			getBackendController().getParser().complain(e);
		}
		Expression commands = getChildren().get(1);
		UserCommand newCommand = new UserCommand(name, getBackendController(), in, args);
		newCommand.addChild(commands);
		getBackendController().getParser().getCommandTable().setCommand(newCommand);
		return 0;
	}

}
