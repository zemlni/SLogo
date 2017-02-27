package backend;

import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.lang.reflect.Constructor;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map.Entry;

public class Parser implements ParserInterface {
	private List<Entry<String, Pattern>> commandSymbols;
	private List<Entry<String, Pattern>> syntaxSymbols;
	private final String WHITESPACE = "\\s+";
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
		variableTable = new VariableTable();
		commandTable = new CommandTable();
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
	private String getSyntaxSymbol(String text) throws CommandError {
		return getSymbol(text, syntaxSymbols);
	}

	private String getCommandSymbol(String text) throws CommandError {
		return getSymbol(text, commandSymbols);
	}

	private String getSymbol(String text, List<Entry<String, Pattern>> list) throws CommandError {
		for (Entry<String, Pattern> e : list) {
			if (match(text, e.getValue())) {
				return e.getKey();
			}
		}
		throw new CommandError();
	}

	@Override
	public double parse(String text) {
		// also need to split by newlines (possibly)
		return parseIntermediate(text.split(WHITESPACE), 0);
	}

	public VariableTable getVariableTable() {
		return variableTable;
	}

	private void complain(Exception e) {

	}

	private double parseIntermediate(String[] split, int index) {
		double[] ret = parse(split, index, 0);
		while (ret[1] < split.length)
			ret = parse(split, ((int) ret[1] + 1), ret[0]);
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
			// e.printStackTrace();
			try {
				cur = commandTable.getCommand(split[index]);
			} catch (Exception e1) {
				// System.out.println("test");
				complain(e1);
				double[] ret = { retVal, index };
				return ret;
			}
		}
		try {
			List<Variable> vars = new ArrayList<Variable>();
			/*
			 * while (index < index + 1 + cur.getNumArgs() && index + 1 <
			 * split.length) { index++; String symbol =
			 * getSyntaxSymbol(split[index]); if (symbol.equals("Constant")) {
			 * vars.add(new Variable(null, Double.parseDouble(split[index]))); }
			 * else if (symbol.equals("Variable")) { vars.add(new Variable(null,
			 * variableTable.getVariable(split[index].substring(1)).getValue()))
			 * ; } else if (symbol.equals("Command")) { vars.add(new
			 * Variable(null, parse(split, index)[0])); } else if
			 * (symbol.equals("Symbol")) { vars.add(new
			 * Variable(split[index].substring(1), 0)); } }
			 */

			for (int i = index; i < index + cur.getNumArgs(); i++) {
				String symbol = getSyntaxSymbol(split[i + 1]);
				if (symbol.equals("Constant")) {
					vars.add(new Variable(null, Double.parseDouble(split[i + 1])));
				} else if (symbol.equals("Variable")) {
					vars.add(new Variable(null, variableTable.getVariable(split[i + 1].substring(1)).getValue()));
				} else if (symbol.equals("Command")) {
					vars.add(new Variable(null, parse(split, index, retVal)[0]));
				} else if (symbol.equals("Symbol")) {
					vars.add(new Variable(split[i + 1].substring(1), 0));
				}
				index = i;
			}
			cur.setArgs(vars);
			/*
			 * double ret = cur.execute(); return index + 1 < split.length ?
			 * parse(split, index + 1): ret;
			 */
			double[] ret = { cur.execute(), index };
			return ret;
		} catch (Exception e) {
			// e.printStackTrace();
			// TODO: do this
			// FrontEndController.showError(String error)
			// figure out what to return
			double[] ret = { 0, index };
			return ret;
		}
	}
}
