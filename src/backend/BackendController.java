package backend;

import frontend.app.FrontEndController;

public class BackendController implements BackendControllerInterface {

	private Parser parser;
	private String language;
	private TurtleModel turtle;
	private FrontEndController fcontroller;

	/**
	 * english is default language
	 */
	public BackendController(FrontEndController frontEndController) {
		//System.out.println(frontEndController);
		this.fcontroller = frontEndController;
		setLanguage("English");
		turtle = new TurtleModel(frontEndController);
		
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
		this.language = language;
		parser = new Parser(this);

	}
	public Parser getParser(){
		return parser;
	}
	
	public String getLanguage(){
		return language;
	}
	
	public FrontEndController getFrontEndController(){
		return fcontroller;
	}

}
