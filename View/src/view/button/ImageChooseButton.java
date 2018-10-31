package view.button;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ErrorAlert;
import view.GUISetup;
import view.TurtleDisplay;
import view.TurtleView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * This class controls what image will be used for the turtle.
 * @author Austin Kao
 */
public class ImageChooseButton extends SLogoButton{
    private static final String RESOURCE_PATH = "data/images/";
    private static final String IMAGE_PATH = "/images/";
    private static final String TITLE = "Open File";

    private GUISetup parentGUI;

    public ImageChooseButton(String label, GUISetup gui) {
        super(label);
        parentGUI = gui;
    }

    @Override
    public void processCommand() {
        Map<Integer, TurtleView> myTurtles = new HashMap<>(parentGUI.getCurrentDisplay().getTurtles());
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(TITLE);
        File defaultFile = new File(RESOURCE_PATH);
        fileChooser.setInitialDirectory(defaultFile);
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            for (int i = 0; i < myTurtles.size(); i++) {
                if (myTurtles.get(i).isActive()) {

                    try {
                        myTurtles.get(i).setView(IMAGE_PATH + file.getName());
                    } catch (Exception ex) {
                        new ErrorAlert(ex);
                    }
                }
            }
        }
    }
}
