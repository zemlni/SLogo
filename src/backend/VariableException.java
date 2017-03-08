package backend;

/**
 * This is what will be thrown in the case when the variable is not found in the
 * variable table or when an invalid syntax is typed by the user
 */
public class VariableException extends SlogoException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VariableException(String error) {
		super("VariableError", error);

	}
}
