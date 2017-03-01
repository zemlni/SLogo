package backend;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import backend.expressions.Expression;

import java.lang.reflect.Constructor;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Map.Entry;

public class Parser implements ParserInterface {
	private List<Entry<String, Pattern>> commandSymbols;
	private List<Entry<String, Pattern>> syntaxSymbols;
	public static final String WHITESPACE_NEWLINE = "\\s+|\\n";
	public static final String WHITESPACE_NEWLINE_COMMENT = WHITESPACE_NEWLINE + "|#.*\\n";
	private VariableTable variableTable;
	private CommandTable commandTable;
	private BackendController controller;

	public Parser(BackendController controller) {
		this.controller = controller;
		ResourceBundle languageResources = ResourceBundle.getBundle(controller.getLanguage());
		ResourceBundle syntaxResources = ResourceBundle.getBundle("Syntax");
		commandSymbols = new ArrayList<Entry<String, Pattern>>();
		syntaxSymbols = new ArrayList<Entry<String, Pattern>>();
		setUpSymbols(languageResources, commandSymbols);
		setUpSymbols(syntaxResources, syntaxSymbols);
		variableTable = new VariableTable(controller.getFrontEndController());
		commandTable = new CommandTable(controller.getFrontEndController());
	}

	private void setUpSymbols(ResourceBundle rb, List<Entry<String, Pattern>> list) {
		Enumeration<String> iter = rb.getKeys();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			String regex = rb.getString(key);
			list.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
		}
	}

	private boolean match(String text, Pattern regex) {
		return regex.matcher(text).matches();
	}

	// fix throw
	public String getSyntaxSymbol(String text) throws CommandException {
		return getSymbol(text, syntaxSymbols);
	}

	public String getCommandSymbol(String text) throws CommandException {
		return getSymbol(text, commandSymbols);
	}

	private String getSymbol(String text, List<Entry<String, Pattern>> list) throws CommandException {
		for (Entry<String, Pattern> e : list) {
			if (match(text, e.getValue())) {
				return e.getKey();
			}
		}
		throw new CommandException(text);
	}

	@Override
	public double parse(String text) {
		String[] split = text.split(WHITESPACE_NEWLINE_COMMENT);
		double[] ret = parse(split, 0, 0);
		while (ret[1] + 1 < split.length)
			ret = parse(split, ((int) ret[1] + 1), ret[0]);
		return ret[0];
	}

	public VariableTable getVariableTable() {
		return variableTable;
	}

	public CommandTable getCommandTable() {
		return commandTable;
	}

	private void complain(Exception e) {
		// TODO: do this
		// FrontEndController.showError(String error)
		// controller.getFrontEndController().showError("");
		e.printStackTrace();
	}

	private Command makeCommand(String name) throws CommandException {
		Command cur = null;
		try {
			Class<?> clazz = Class.forName("backend.commands." + getCommandSymbol(name) + "Command");
			Constructor<?> ctor = clazz.getDeclaredConstructor(controller.getClass());
			cur = (Command) ctor.newInstance(controller);
			return cur;
		} catch (Exception e) {
			cur = commandTable.getCommand(name);
			return cur;
		}
	}

	private List<Variable> parseArgs(String[] split, int index, double retVal, Command cur) throws CommandException {
		List<Variable> vars = new ArrayList<Variable>();
		int i;
		for (i = index; i < index + cur.getNumArgs(); i++) {
			if (i + 1 < split.length) {
				Expression expr = null;
				try {
					Class<?> clazz = Class
							.forName("backend.expressions." + getSyntaxSymbol(split[i + 1]) + "Expression");
					Constructor<?> ctor = clazz.getDeclaredConstructor(getClass(), Command.class);
					expr = (Expression) ctor.newInstance(this, cur);
				} catch (Exception e) {
					e.printStackTrace();
					throw new CommandException(split[i + 1]);
				}
				List<Variable> tempVars = expr.parse(split, i + 1, retVal);
				vars.add(tempVars.get(0));
				if (tempVars.size() > 1) {
					i += tempVars.get(1).getValue();
					index += tempVars.get(1).getValue();
				}
			}
		}
		vars.add(new Variable(null, i));
		return vars;
	}

	public double[] parse(String[] split, int index, double retVal) {
		Command cur = null;
		double[] ret = { retVal, index };
		try {
			cur = makeCommand(split[index]);
		} catch (Exception e) {
			complain(e);
			return ret;
		}
		try {
			List<Variable> vars = parseArgs(split, index, retVal, cur);
			ret[1] = (int) vars.get(vars.size() - 1).getValue();
			vars.remove(vars.size() - 1);
			cur.setArgs(vars);
			ret[0] = cur.execute();
			return ret;
		} catch (Exception e) {
			complain(e);
			return ret;
		}
	}
}
