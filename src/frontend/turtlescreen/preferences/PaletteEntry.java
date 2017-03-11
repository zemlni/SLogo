package frontend.turtlescreen.preferences;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PaletteEntry extends VBox {
	private int index;
	
	public PaletteEntry(Node node, int index){
		Text indexText = new Text(Integer.toString(index));
		getChildren().addAll(node, indexText);
		this.index = index;
	}
	
	public int getIndex(){
		return index;
	}
	
	@Override
	public boolean equals(Object o) {
	        if (o == this) { return true; }
		if(o instanceof PaletteEntry){
			if(((PaletteEntry) o).index == this.index){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
	    int ans = 17;
	    ans *= 37 + index;
	    return ans;
	}
}
