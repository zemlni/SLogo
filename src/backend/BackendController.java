package backend;

import frontend.FrontEndController;

public class BackendController implements BackendControllerInterface {

	private Parser parser;
	private String language;
	private TurtleModel turtle;

	/**
	 * english is default language
	 */
	public BackendController(FrontEndController fcontroller) {
		setLanguage("English");
		turtle = new TurtleModel(fcontroller);
	}
	
	public TurtleModel getTurtleModel(){
		return turtle;
	}

	@Override
	public void evaluate(String command) {
		double ret = parser.parse(command);
		//notify front end of return value
	}

	@Override
	public void setLanguage(String language) {
		parser = new Parser(language);

	}

}
