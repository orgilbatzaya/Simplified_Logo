package view;

import javafx.application.Application;
import javafx.stage.Stage;

public class SLogoMain extends Application {
    private static final String TITLE = "SLogo Title";

    @Override
    public void start(Stage stage) {
        GUISetup gui = new GUISetup();
        //Can't do this b/c this will cause a cyclical dependency
        //BackMain back = new BackMain(gui.getConsoleCommands(),gui.getLanguage(),gui.getTurtleDisplay());
        stage = new Stage();
        stage.setTitle(TITLE);
        stage.setScene(gui.getScene());
        stage.show();
    }

    public static void main(String[] args) { launch(args);
    }
}
