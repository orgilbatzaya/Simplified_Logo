package view;

import javafx.application.Application;
import javafx.stage.Stage;
//import model.ProgramParser;

public class SLogoMain extends Application {
    private static final String TITLE = "SLogo Title";

    @Override
    public void start(Stage stage) {
        GUISetup gui = new GUISetup();
        stage = new Stage();
        stage.setTitle(TITLE);
        stage.setScene(gui.getScene());
        stage.show();
    }

    public static void main(String[] args) { launch(args);
    }
}
