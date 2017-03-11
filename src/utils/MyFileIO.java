package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.stage.FileChooser;
import utils.language.Language;

public class MyFileIO {

	private MyFileIO() { }
	
	public static String readTextFile(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    return sb.toString();
		} finally {
		    br.close();
		}
	}
	
	public static void saveTextFile(String filename, String text) throws IOException {
	  File file = new File(filename);
	  BufferedWriter out = new BufferedWriter(new FileWriter(file)); 
	  out.write(text);
	  out.close();
	}
	
	public static File chooseFile(FileChooserOption fileOp) {
		return chooseFile(fileOp, new ArrayList<FileChooser.ExtensionFilter>());
	}
	public static File chooseFile(FileChooserOption fileOp, FileChooser.ExtensionFilter extFilters) {
		List<FileChooser.ExtensionFilter> extFilterList = new ArrayList<>();
		extFilterList.add(extFilters);
		return chooseFile(fileOp, extFilterList);
	}
	public static File chooseFile(FileChooserOption fileOp, List<FileChooser.ExtensionFilter> extFilters) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File("."));
		
		fileChooser.getExtensionFilters().addAll(extFilters);
		
		if (fileOp == FileChooserOption.OPEN) {
			fileChooser.setTitle(Language.getWord("Open"));
			return fileChooser.showOpenDialog(null);
		} else {
			fileChooser.setTitle(Language.getWord("SaveAs"));
			return fileChooser.showSaveDialog(null);
		}
	}
	
	
	
}
