package backend;

public abstract class SlogoException extends Exception {
	private String errorType;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SlogoException(String errorType, String error){
		super(error);
		this.errorType = errorType;
	}
	
	public String getErrorType(){
		return errorType;
	}
}
