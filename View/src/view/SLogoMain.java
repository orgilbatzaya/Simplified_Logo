package view;

import javafx.application.Application;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * This class is mainly intended to launch the SLogo application.
 * @author Austin Kao
 */

public class SLogoMain extends Application implements ViewResourceBundles{
    private static final String SLOGO_TITLE = "slogoTitle";

    private Stage myStage;

    public void useKeyboardShortcut(KeyCode keyCode) {
        if(keyCode == KeyCode.ESCAPE) {
            myStage.close();
        }
    }

    @Override
    public void start(Stage stage) {
        GUISetup gui = new GUISetup();
        stage = new Stage();
        myStage = stage;
        stage.setTitle(getDefault(SLOGO_TITLE));
        stage.setScene(gui.getScene());
        stage.show();
        gui.getScene().setOnKeyPressed(e -> useKeyboardShortcut(e.getCode()));
    }

    public static void main(String[] args) { launch(args);}
}
