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
import backend.ParserInterface;
import backend.SlogoException;
import backend.VariableException;
import backend.VariableTable;

/**
 * @author nikita This class is the implementation of the parser. There is
 *         functionality to parse the input string into a tree of commands,
 *         variables, constants and various other expressions. Errors are
 *         handled and the front end is alerted accordingly. This class
 *         maintains instances of the command table and the variable table as
 *         well.
 */
public class TreeParser implements ParserInterface {
	private BackendController controller;
	private List<Entry<String, Pattern>> commandSymbols;
	private List<Entry<String, Pattern>> syntaxSymbols;
	public static final String WHITESPACE_NEWLINE = "\\s+|\\n+";
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

	/**
	 * get the syntax symbol that this string represents
	 * 
	 * @param text
	 *            the string to translate to a syntax element
	 * @return the string that represents the syntax element (used to create
	 *         classes in reflection)
	 * @throws CommandException
	 *             when the symbol was not matched to a valid element of syntax
	 */
	public String getSyntaxSymbol(String text) throws CommandException {
		return getSymbol(text, syntaxSymbols);
	}

	/**
	 * get the command that this string represents
	 * 
	 * @param text
	 *            the string to translate to a command
	 * @return the string that represents the command (used to create classes in
	 *         reflection)
	 * @throws CommandException
	 *             when the symbol was not matched to a command
	 */
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

	/**
	 * alert front end to notify user of error.
	 * 
	 * @param e
	 *            the exception that was thrown
	 */
	public void complain(Exception e) {
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
			Class<?> clazz = Class.forName("backend.commands." + getCommandSymbol(name.get()) + "Command");
			Constructor<?> ctor = clazz.getDeclaredConstructor(name.getClass(), controller.getClass());
			cur = (Command) ctor.newInstance(name, controller);
			cur.setInfo(name);
			return cur;
		} catch (Exception e) {
			try {
				Command temp = commandTable.getCommand(name.get());
				cur = new Command(name, controller);
				cur.setInfo(name);
				cur.setNumArgs(temp.getNumArgs());
				return cur;
			} catch (Exception e1) {
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

	/**
	 * parse the expression represented by the string input. create the
	 * corresponding expressions in a tree form with. Breakpoints are created,
	 * but applied at execution.
	 * 
	 * @param input
	 *            the string to be parsed
	 * @param breakPoints
	 *            a list of the lines on which to break
	 * @return the root expression of the tree
	 */
	@Override
	public Expression parse(String input, List<Integer> breakPoints) {
		String[] tempSplit = input.split("\\n+");
		String tempString = "";
		for (int i = 0; i < tempSplit.length; i++) {
			if (breakPoints.contains(new Integer(i + 1)))
				tempSplit[i] = "!" + tempSplit[i];
			if (tempSplit[i].trim().length() > 0 && !(tempSplit[i].trim().charAt(0) == '#')) {
				tempString += tempSplit[i] + " ";
			}
		}
		String[] split = tempString.split(WHITESPACE_NEWLINE);
		Input in = new Input(split, breakPoints);
		Expression top = new ListStartExpression(controller);
		while (in.getIndex() < in.getLength()) {
			in = parse(in);
			if (in.getExpression() != null) {
				top.addChild(in.getExpression());
				in.getExpression().setParent(top);
				
				in.incrementIndex();
			}
		}
		return top;
	}

	private Input parse(Input in) {
		System.out.println(in.get());
		while (in.getIndex() < in.getLength() && in.get().trim().equals(""))
			in.incrementIndex();
		Expression cur = null;
		try {
			cur = makeExpression(in);
		} catch (Exception e) {
			complain(e);
			in.finish();
			return in;
		}
		int numArgs = cur.getNumChildren();
		while (numArgs > 0 && in.getIndex() < in.getLength() - 1) {
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
