package frontend;
	
	/**
	 * Class which will implement variables for SLogo. Will be kept in back-end. This is a
	 * placeholder so that the interfaces will compile for planning submission.
	 */
public class Variable {
	private String name;
	private Object value;
	
	public Variable(String varName, Object varValue){
		name = varName;
		value = varValue;
	}
	
	public String getKey(){
		return name;
	}
	
	public Object getValue(){
		return value;
	}
}
