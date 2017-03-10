package frontend.preferences;

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
		if(o instanceof PaletteEntry){
			if(((PaletteEntry) o).index == this.index){
				return true;
			}
		}
		return false;
		
	}
}
