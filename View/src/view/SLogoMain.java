package view;

import javafx.application.Application;
import javafx.stage.Stage;
import model.BackMain;

import java.util.List;
import java.util.ResourceBundle;

/**
 * This class is mainly intended to launch the SLogo application.
 * @author Austin Kao
 */

public class SLogoMain extends Application {
    private static final String SLOGO_TITLE = "slogoTitle";

    @Override
    public void start(Stage stage) {
        GUISetup gui = new GUISetup();

        stage = new Stage();
        stage.setTitle(gui.getDefaultValues().getString(SLOGO_TITLE));
        stage.setScene(gui.getScene());
        stage.show();
        //add language resource bundle as first parameter of backMain
        BackMain back = new BackMain(gui.getTurtleParams());
        back.performCommands(gui.getConsole().getNextCommand());
        List<String> actionList = back.getMyTurtleActions();
        List<Double> actionArgs = back.getMyTurtleActionsArgs();
        ActionInterpreter actInterpret = new ActionInterpreter();
        actInterpret.performActions(actionList,actionArgs);

    }

    public static void main(String[] args) { launch(args);
    }
}
