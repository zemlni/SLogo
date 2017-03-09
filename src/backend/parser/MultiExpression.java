package backend.parser;

import backend.BackendController;

public abstract class MultiExpression extends Expression {

	private String arg;

	public MultiExpression(Input info, BackendController controller, String expressionName) {
		super(info, controller);
		String arg = "";
		if (info != null) {
			try {
				int numBrackets = 0;
				while (numBrackets >= 0) {
					info.incrementIndex();
					info.incrementCount();
					String curArg = info.get();
					arg += curArg + " ";
					String symbol = getBackendController().getParser().getSyntaxSymbol(curArg);
					if (symbol.equals(expressionName + "End"))
						numBrackets--;
					else if (symbol.equals(expressionName + "Start"))
						numBrackets++;
				}
				arg = arg.substring(0, arg.length() - 2);
			} catch (Exception e) {
				getBackendController().getParser().complain(e);
			}
		}
		this.arg = arg;
	}

	public String getArg() {
		return arg;
	}
}
