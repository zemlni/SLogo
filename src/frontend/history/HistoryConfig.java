package frontend.history;

import java.util.List;
import frontend.IViewConfig;

public class HistoryConfig implements IViewConfig {

	private static final long serialVersionUID = -3798232904122606107L;
	private List<String> histories;
	
	public HistoryConfig(List<String> histories) {
		this.histories = histories;
	}
	public List<String> getHistories() {
		return histories;
	}
	
}
