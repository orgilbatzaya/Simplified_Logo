package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

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

    public static void main(String[] args) {
        launch(args);
    }
}
