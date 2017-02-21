package slogo_team08;

/**
 * This is the interface for Variable objects. This is both external and
 * internal API. update will be called from front end when the user updates a
 * variable through the UI.
 */
public interface Variable {

	/**
	 * get the key (name) of this variable
	 * 
	 * @return the key(name) of the variable
	 */
	public String getKey();

	/**
	 * update the value of this object to the new value
	 * 
	 * @param newValue
	 *            the new value to be held by this variable
	 */
	public void update(Object newValue);

	/**
	 * get the value of this variable.
	 * 
	 * @return the value of this variable
	 */
	public Object getValue();
}
