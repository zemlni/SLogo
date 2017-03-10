package frontend.nonfxml.config;

import frontend.nonfxml.IViewConfig;
import frontend.nonfxml.view.DisplayConfig;

public class TurtleScreenConfig implements IViewConfig {

	private static final long serialVersionUID = -3476116923436237343L;

	private DisplayConfig displayConfig;
	
	public TurtleScreenConfig(DisplayConfig displayConfig) {
		this.displayConfig = displayConfig;
	}
	
	public DisplayConfig getDisplayConfig() {
		return displayConfig;
	}
	
}
