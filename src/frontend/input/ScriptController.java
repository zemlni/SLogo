package frontend.input;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import frontend.IViewController;
import frontend.frontend.FrontEndController;
import utils.FileChooserOption;
import utils.MyFileIO;
import utils.javafx.FX;


/**
 * The ScriptController is in charge of the Script tab where the user
 * can write scripts of SLogo commands that can be executed.
 * 
 * @author Matthew Tribby
 */
public class ScriptController implements InputController, IViewController {

    private ScriptView view;
    private FrontEndController frontEnd;

    public ScriptController (ScriptView view) {
        this.view = view;
    }

    public void setFrontEndController (FrontEndController frontEnd) {
        this.frontEnd = frontEnd;
    }

    private String getText () {
        return view.getScriptText();
    }

    private void setText (String text) {
        view.setScriptText(text);
    }

    private List<Integer> getBreakPoints () {
        String breakPointText = view.getBreakPointsText();
        Scanner scanner = new Scanner(breakPointText);
        scanner.useDelimiter("\\s+");
        List<Integer> breakPoints = new ArrayList<>();
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                breakPoints.add(scanner.nextInt());
            }
            else {
                scanner.next();
            }
        }
        scanner.close();
        return breakPoints;
    }

    public void run () {
        frontEnd.evaluate(getText());
    }

    public void debug () {
        frontEnd.debug(getText(), getBreakPoints());
    }

    public void step () {
        frontEnd.step();
    }

    public void clearArea () {
        setText("");
    }

    public void openFile () {
        File file = MyFileIO.chooseFile(FileChooserOption.OPEN);
        if (file != null) {
            try {
                String scriptText = MyFileIO.readTextFile(file.getAbsolutePath());
                setText(scriptText);
            }
            catch (IOException e) {
                showError("FileOpenError", file.getName());
            }
        }
    }

    public void saveFile () {
        File file = MyFileIO.chooseFile(FileChooserOption.SAVE);
        if (file != null) {
            try {
                MyFileIO.saveTextFile(file.getAbsolutePath(), getText());
            }
            catch (IOException e) {
                showError("FileSaveError", file.getName());
            }
        }
    }

    @Override
    public void showError (String errorMsgKey, String content) {
        FX.alertError("ErrorTitle", errorMsgKey, content);
    }

    @Override
    public void showText (String text) {

    }

    @Override
    public void appendText (String text) {
        setText(getText() + text + System.lineSeparator());
    }

}
