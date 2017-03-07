package backend.parser;

import backend.BackendController;
import backend.Variable;
import backend.parser.Expression;

public class GroupStartExpression extends Expression {

	//refactor, very similar to liststartexpression
	public GroupStartExpression(Input info, BackendController controller) {
		super(info, controller);
		info.incrementIndex();
		Expression command = getBackendController().getParser().parse(info.get(), info.getBreakPoints()).getChildren()
				.get(0);
		String arg = "";
		try {
			int numBrackets = 0;
			while (numBrackets >= 0) {
				info.incrementIndex();
				info.incrementCount();
				String curArg = info.get();
				arg += curArg + " ";
				String symbol = getBackendController().getParser().getSyntaxSymbol(curArg);
				if (symbol.equals("GroupEnd"))
					numBrackets--;
				else if (symbol.equals("GroupStart"))
					numBrackets++;
			}
			arg = arg.substring(0, arg.length() - 2);
		} catch (Exception e) {
			getBackendController().getParser().complain(e);
		}
		command.setParent(this);
		command.addChildren(getBackendController().getParser().parse(arg, info.getBreakPoints()).getChildren());
		for (Expression expr: command.getChildren())
			expr.setParent(command);
		this.addChild(command);
	}

	@Override
	public Variable evaluate() {
		return getChildren().get(0).evaluate();
	}

}
