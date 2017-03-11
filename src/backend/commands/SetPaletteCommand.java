package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

/**
 * @author nikita Set the Palette.
 */
public class SetPaletteCommand extends Command {

	public SetPaletteCommand(Input info, BackendController controller) {
		super(info, controller, 4);
	}

	/**
	 * Set the Palette.
	 * 
	 * @return the index of the set palette
	 */
	@Override
	public double execute() {
		List<Variable> args = getArgs();
		int[] params = new int[4];
		for (int i = 0; i < params.length; i++) {
			params[i] = (int) args.get(i).getValue();
		}
		getBackendController().getFrontEndController().getDisplayController().setPalette(params);
		return params[0];
	}

}
