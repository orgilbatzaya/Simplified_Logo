package view;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class is mainly intended to launch the SLogo application.
 * @author Austin Kao
 */

public class SLogoMain extends Application implements ViewResourceBundles{
    private static final String SLOGO_TITLE = "slogoTitle";

    @Override
    public void start(Stage stage) {
        GUISetup gui = new GUISetup();

        stage = new Stage();
        stage.setTitle(myDefaults.getString(SLOGO_TITLE));
        stage.setScene(gui.getScene());
        stage.show();
    }

    public static void main(String[] args) { launch(args);}
}
