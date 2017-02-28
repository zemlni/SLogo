package backend;

import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.lang.reflect.Constructor;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Map.Entry;

public class Parser implements ParserInterface {
	private List<Entry<String, Pattern>> commandSymbols;
	private List<Entry<String, Pattern>> syntaxSymbols;
	public static final String WHITESPACE_NEWLINE = "\\s+|\\n";
	// TODO: initialize these
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
		return parseIntermediate(text.split(WHITESPACE_NEWLINE), 0);
	}

	public VariableTable getVariableTable() {
		return variableTable;
	}

	public CommandTable getCommandTable() {
		return commandTable;
	}

	private void complain(Exception e) {

	}

	private double parseIntermediate(String[] split, int index) {
		double[] ret = parse(split, index, 0);
		while (ret[1] + 1 < split.length) {
			ret = parse(split, ((int) ret[1] + 1), ret[0]);
			System.out.println("TEST1");
		}
		return ret[0];
	}

	private double[] parse(String[] split, int index, double retVal) {
		Class<?> clazz = null;
		Command cur = null;
		try {
			clazz = Class.forName("commands." + getCommandSymbol(split[index]) + "Command");
			Constructor<?> ctor = clazz.getDeclaredConstructor(controller.getClass());
			cur = (Command) ctor.newInstance(controller);
		} catch (Exception e) {
			//e.printStackTrace();
			try {
				cur = commandTable.getCommand(split[index]);
			} catch (Exception e1) {
				complain(e1);
				double[] ret = { retVal, index };
				return ret;
			}
		}
		try {
			List<Variable> vars = new ArrayList<Variable>();
			int i;
			for (i = index; i < index + cur.getNumArgs(); i++) {
				if (i + 1 < split.length) {
					String symbol = getSyntaxSymbol(split[i + 1]);
					if (symbol.equals("Constant")) {
						vars.add(new Variable(null, Double.parseDouble(split[i + 1])));
					} else if (symbol.equals("Variable")) {
						vars.add(new Variable(null, variableTable.getVariable(split[i + 1].substring(1)).getValue()));
					} else if (symbol.equals("Command")) {
						double [] recurse = parse(split, i + 1, retVal);
						vars.add(new Variable(null, recurse[0]));
						double diff = recurse[1] - (i + 1);
						i += diff;
						index += diff;
					} else if (symbol.equals("Symbol")) {
						vars.add(new Variable(split[i + 1].substring(1), 0));
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
						vars.add(new Variable(arg, 0));
					}
				}
			}
			
			index = i;
			cur.setArgs(vars);
			double[] ret = { cur.execute(), index };
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: do this
			// FrontEndController.showError(String error)
			// figure out what to return
			// controller.getFrontEndController().showError("");
			double[] ret = { retVal, index };
			return ret;
		}
	}
}
