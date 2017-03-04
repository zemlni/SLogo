package backend.parser;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import backend.BackendController;
import backend.Command;
import backend.CommandException;
import backend.CommandTable;
import backend.SlogoException;
import backend.VariableException;
import backend.VariableTable;

public class TreeParser {
	private BackendController controller;
	private List<Entry<String, Pattern>> commandSymbols;
	private List<Entry<String, Pattern>> syntaxSymbols;
	public static final String WHITESPACE_NEWLINE = "\\s+|\\n+";
	public static final String WHITESPACE_NEWLINE_COMMENT = WHITESPACE_NEWLINE + "|#.*\\n";
	private CommandTable commandTable;
	private VariableTable variableTable;

	public TreeParser(BackendController controller) {
		this.controller = controller;
		ResourceBundle languageResources = ResourceBundle.getBundle(controller.getLanguage());
		ResourceBundle syntaxResources = ResourceBundle.getBundle("Syntax");
		commandSymbols = new ArrayList<Entry<String, Pattern>>();
		syntaxSymbols = new ArrayList<Entry<String, Pattern>>();
		setUpSymbols(languageResources, commandSymbols);
		setUpSymbols(syntaxResources, syntaxSymbols);
		commandTable = new CommandTable(controller.getFrontEndController());
		variableTable = new VariableTable(controller.getFrontEndController());
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

	String getSyntaxSymbol(String text) throws CommandException {
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

	public VariableTable getVariableTable() {
		return variableTable;
	}

	public CommandTable getCommandTable() {
		return commandTable;
	}

	protected void complain(Exception e) {
		e.printStackTrace();
		String error = "";
		String message = "";
		if (e instanceof IndexOutOfBoundsException)
			error = "OutOfBoundsError";
		else {
			error = ((SlogoException) e).getErrorType();
			message = ((SlogoException) e).getMessage();
		}
		controller.getFrontEndController().showError(error, message);
	}

	private Command makeCommand(Input name) throws CommandException {
		Command cur = null;
		try {
			System.out.println("TRYING TO MAKE REAL COMMAND: " + name.get());
			System.out.println(getCommandSymbol(name.get()));
			Class<?> clazz = Class.forName("backend.commands." + getCommandSymbol(name.get()) + "Command");
			Constructor<?> ctor = clazz.getDeclaredConstructor(name.getClass(), controller.getClass());
			cur = (Command) ctor.newInstance(name, controller);
			cur.setInfo(name);
			return cur;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				System.out.println("TEST");
				cur = commandTable.getCommand(name.get());
				cur.setInfo(name);
				System.out.println("MADE USER COMMAND IN TREE PARSER: " + name.get());
				return cur;
			} catch (Exception e1) {
				System.out.println("MAKE NEW BLANK COMMAND: " + name.get());
				cur = new Command(name, controller);
				return cur;
			}
		}
	}

	private Expression makeExpression(Input name) throws VariableException {
		Expression expr = null;
		try {
			if (getSyntaxSymbol(name.get()).equals("Command")) {
				expr = makeCommand(name);
			} else {
				Class<?> clazz = Class.forName("backend.parser." + getSyntaxSymbol(name.get()) + "Expression");
				Constructor<?> ctor = clazz.getDeclaredConstructor(name.getClass(), controller.getClass());
				expr = (Expression) ctor.newInstance(name, controller);
			}
		} catch (Exception e) {
			throw new VariableException(name.get());
		}
		return expr;
	}

	public Expression parse(String input, List<Integer> breakPoints) {
		// split by newline and comments, keep track of line numbers to create
		// commands with breakpoints

		// split each line that is not empty or a comment by spaces
		// call parse
		String[] split = input.split(WHITESPACE_NEWLINE_COMMENT);
		Input in = new Input(split, breakPoints);
		Expression top = new ListStartExpression(controller);
		while (in.getIndex() < in.getLength()) {
			in = parse(in);
			top.addChild(in.getExpression());
			in.incrementIndex();
		}
		return top;
	}

	private Input parse(Input in) {
		// create appropriate Expression based on getSymbol and use reflection
		while (in.get().trim().equals(""))
			in.incrementIndex();
		System.out.println("INPUT: " + in.get());
		Expression cur = null;
		try {
			cur = makeExpression(in);
		} catch (Exception e) {
			complain(e);
		}
		// check how many arguments it needs
		// recursive call: if needs more args: childExpression = parse
		// cmd.childExpression = childExpression
		int numArgs = cur.getNumChildren();
		while (numArgs > 0) {
			in.incrementIndex();
			Input child = parse(in);
			child.getExpression().setParent(cur);
			cur.addChild(child.getExpression());
			numArgs--;
		}
		in.setExpression(cur);
		return in;
	}
}
