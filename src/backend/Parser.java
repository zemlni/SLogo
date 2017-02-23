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

	public Parser(String language) {
		ResourceBundle languageResources = ResourceBundle.getBundle(language);
		ResourceBundle syntaxResources = ResourceBundle.getBundle("Syntax");
		commandSymbols = new ArrayList<Entry<String, Pattern>>();
		syntaxSymbols = new ArrayList<Entry<String, Pattern>>();
		setUpSymbols(languageResources, commandSymbols);
		setUpSymbols(syntaxResources, syntaxSymbols);
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
		//also need to split by newlines (possibly)
		return parse(text.split(WHITESPACE));
	}

	private double parse(String[] split) {

		try {
			Class<?> clazz = Class.forName("commands." + getCommandSymbol(split[0]) + "Command");
			Constructor<?> ctor = clazz.getDeclaredConstructor();
			Command cur = (Command) ctor.newInstance();
			List<Variable> vars = new ArrayList<Variable>();
			for (int i = 0; i < cur.getNumArgs(); i++) {
				String symbol = getSyntaxSymbol(split[i + 1]);
				if (symbol.equals("Constant")) {
					vars.add(new Variable(Double.parseDouble(split[i + 1])));
				} else if (symbol.equals("Command"))
					return parse(Arrays.copyOfRange(split, i + 1, split.length));
			}

			return cur.execute();
		} catch (Exception e) {
			// TODO: do this
			// FrontEndController.showError(String error)
			// figure out what to return
			return 0;
		}
	}

	public static void main(String[] args) {
		Parser parser = new Parser("English");
		String command = "fd fd 50";
		System.out.println(parser.parse(command));
	}

}
