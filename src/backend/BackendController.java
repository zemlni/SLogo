package backend;

public class BackendController implements BackendControllerInterface {

	private Parser parser;
	private String language;

	/**
	 * english is default language
	 */
	public BackendController() {
		setLanguage("English");
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

}
