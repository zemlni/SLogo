package backend;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import expressions.Expression;

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
	private String getSyntaxSymbol(String text) throws CommandException {
		return getSymbol(text, syntaxSymbols);
	}

	private String getCommandSymbol(String text) throws CommandException {
		return getSymbol(text, commandSymbols);
	}

	private boolean isDefinedLangCommand(String name) {
		try {
			getCommandSymbol(name);
			return true;
		} catch (CommandException e) {
			return false;
		}
	}

	private boolean isDefinedUserCommand(String name) {
		try {
			commandTable.getCommand(name);
			return true;
		} catch (CommandException e) {
			return false;
		}
	}

	public boolean isDefinedCommand(String name) {
		return isDefinedUserCommand(name) || isDefinedLangCommand(name);
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
		return parseIntermediate(text.split(WHITESPACE_NEWLINE_COMMENT), 0);
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

	private double parseIntermediate(String[] split, int index) {
		double[] ret = parse(split, index, 0);
		while (ret[1] + 1 < split.length) {
			ret = parse(split, ((int) ret[1] + 1), ret[0]);
		}
		return ret[0];
	}

	private Command makeCommand(String name) throws CommandException {
		Command cur = null;
		try {
			Class<?> clazz = Class.forName("commands." + getCommandSymbol(name) + "Command");
			Constructor<?> ctor = clazz.getDeclaredConstructor(controller.getClass());
			cur = (Command) ctor.newInstance(controller);
			return cur;
		} catch (Exception e) {
			cur = commandTable.getCommand(name);
			return cur;
		}
	}

	private List<Variable> parseArgs(String[] split, int index, double retVal, int numArgs) throws CommandException {
		List<Variable> vars = new ArrayList<Variable>();
		int i;
		for (i = index; i < index + numArgs; i++) {
			if (i + 1 < split.length) {
				Expression expr = null;

				try {
					Class<?> clazz = Class.forName("expressions." + getSyntaxSymbol(split[i + 1]) + "Expression");
					Constructor<?> ctor = clazz.getDeclaredConstructor(getClass());
					expr = (Expression) ctor.newInstance(this);
				} catch (Exception e) {

				}
				List<Variable> tempVars = expr.parse(split, i + 1, retVal);
				vars.add(tempVars.get(0));
				
				String symbol = getSyntaxSymbol(split[i + 1]);
				if (symbol.equals("Constant")) {
					vars.add(new Variable(null, Double.parseDouble(split[i + 1])));
				} else if (symbol.equals("Variable")) {
					String varName = split[i + 1].substring(1);
					if (variableTable.contains(varName))
						vars.add(variableTable.getVariable(varName));
					else
						vars.add(new Variable(varName, 0));
				} else if (symbol.equals("Command")) {
					if (isDefinedCommand(split[i + 1])) {
						double[] recurse = parse(split, i + 1, retVal);
						vars.add(new Variable(null, recurse[0]));
						double diff = recurse[1] - (i + 1);
						i += diff;
						index += diff;
					} else {
						vars.add(new Variable(split[i + 1], 0));
					}
				} else if (symbol.equals("ListStart")) {
					int temp = 1;
					symbol = getSyntaxSymbol(split[i + 1 + temp]);
					String arg = "";
					while (!symbol.equals("ListEnd")) {
						arg += split[i + 1 + temp] + " ";
						temp++;
						symbol = getSyntaxSymbol(split[i + 1 + temp]);
					}
					index += temp;
					i += temp;
					vars.add(new Variable(arg));
				}
			}
		}
		vars.add(new Variable(null, i));
		return vars;
	}

	private double[] parse(String[] split, int index, double retVal) {
		Command cur = null;
		double[] ret = { retVal, index };
		try {
			cur = makeCommand(split[index]);
		} catch (Exception e) {
			complain(e);
			return ret;
		}
		try {
			List<Variable> vars = parseArgs(split, index, retVal, cur.getNumArgs());
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
