package backend;

public class VariableException extends SlogoException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VariableException(String error){
		super("VariableError", error);
		
	}
}
