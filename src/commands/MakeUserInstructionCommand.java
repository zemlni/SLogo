package commands;

import backend.BackendController;
import backend.Command;

public class MakeUserInstructionCommand extends Command {
	private final String WHITESPACE_NEWLINE = "\\s+|\\n";
	
	public MakeUserInstructionCommand(BackendController controller) {
		super(controller);
		setNumArgs(3);
	}

	@Override
	public double execute() {
		String name = getArgs().get(0).getKey();
		String[] vars = getArgs().get(1).getKey().split(WHITESPACE_NEWLINE);
		String commands = getArgs().get(2).getKey();
		UserCommand newCommand = new UserCommand(name, vars, commands, getBackendController());
		newCommand.setNumArgs(vars.length);
		getBackendController().getParser().getCommandTable().setCommand(newCommand);
		return 0;
	}

}
