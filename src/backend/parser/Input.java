package backend.parser;

import java.util.List;

/**
 * @author nikita This class represents the input provided by the user. It has
 *         the input string of commands, and tracks positions of parsing,
 *         breakpoints, current and previous expressions when they have been
 *         evaluated, and other related elements.
 */
public class Input {
	private Expression expr;
	private Expression previous;
	private int index;
	private String[] input;
	private List<Integer> breakPoints;
	private int count;

	public Input(String[] input, List<Integer> breakPoints) {
		this(input, 0, breakPoints);
	}

	public Input(String[] input, int index, List<Integer> breakPoints) {
		this.input = input;
		this.index = index;
		this.breakPoints = breakPoints;
		count = 0;
	}

	public void setExpression(Expression expr) {
		previous = this.expr;
		this.expr = expr;
	}

	public Expression getExpression() {
		return expr;
	}

	public int getIndex() {
		return index;
	}

	public void incrementIndex() {
		index++;
	}

	public void decrementIndex() {
		index--;
	}

	public String[] getInput() {
		return input;
	}

	public int getLength() {
		return input.length;
	}

	public String get() {
		return input[index];
	}

	public void set(String str) {
		input[index] = str;
	}

	public List<Integer> getBreakPoints() {
		return breakPoints;
	}

	public void incrementCount() {
		this.count++;
	}

	public void decrementByCount() {
		index -= count;
	}

	public Expression getPrevious() {
		return previous;
	}
}
