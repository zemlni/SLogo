package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Expression;
import backend.parser.ListStartExpression;
import backend.parser.Input;

public class MakeUserInstructionCommand extends Command {

	public MakeUserInstructionCommand(Input in, BackendController controller) {
		super(in, controller, 3);
		in.incrementIndex();
		in.incrementIndex();
		System.out.println("IN HERE");
		ListStartExpression expr = new ListStartExpression(in, controller);
		in.decrementByCount();
		in.decrementIndex();
		System.out.println("IN HERE");
		int numArgs = expr.getChildren().size();
		Command newCommand = new Command(in, controller);
		newCommand.setNumArgs(numArgs);
		getBackendController().getParser().getCommandTable().setCommand(newCommand);
		in.decrementIndex();
		System.out.println("IN HERE");
	}

	@Override
	public double execute() {
		//String name = getArgs().get(0).getKey();
		String name = getChildren().get(0).evaluate().getKey();
		String[] vars = new String[getChildren().get(1).getChildren().size()];
		for (int i = 0; i < vars.length; i++){
			vars[i] = getChildren().get(1).getChildren().get(i).evaluate().getKey();
		}
		
		Expression commands = getChildren().get(2);
		UserCommand newCommand = new UserCommand(name, vars, commands, getBackendController(), getInfo());
		getBackendController().getParser().getCommandTable().setCommand(newCommand);
		return 0;
	}

}
