package utils.language;

import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Language {
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/ui/";

	private static ObjectProperty<String> language;
	static {
		language = new SimpleObjectProperty<>("English");
	}

	private static String get(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language.getValue());
		return bundle.getString(key);
	}

	public static String getWord(String key) {
		return get(key);
	}

	static void setLanguage(String newLanguage) {
		language.setValue(newLanguage);
	}

	public static String getLanguage() {
		return language.getValue();
	}

	public static StringBinding createStringBinding(String key) {
		return Bindings.createStringBinding(() -> get(key), language);
	}

}
