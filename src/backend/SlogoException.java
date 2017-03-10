package backend;

/**
 * @author nikita This is the Exception class from which command exception and
 *         variable exception extend.
 */
public abstract class SlogoException extends Exception {
	private String errorType;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SlogoException(String errorType, String error) {
		super(error);
		this.errorType = errorType;
	}

	public String getErrorType() {
		return errorType;
	}
}
