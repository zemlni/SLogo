package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.Parser;

public class MakeUserInstructionCommand extends Command {

	public MakeUserInstructionCommand(BackendController controller) {
		super(controller, 3);
	}

	@Override
	public double execute() {
		String name = getArgs().get(0).getKey();
		getBackendController().getParser();
		String[] vars = getArgs().get(1).getInfo().split(Parser.WHITESPACE_NEWLINE);
		String commands = getArgs().get(2).getInfo();
		UserCommand newCommand = new UserCommand(name, vars, commands, getBackendController());
		newCommand.setNumArgs(vars.length);
		getBackendController().getParser().getCommandTable().setCommand(newCommand);
		return 0;
	}

}
