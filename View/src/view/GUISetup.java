package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.ResourceBundle;

/**
 * This class sets up the GUI for SLogo. The method that creates most of the GUI is createGUI().
 * The default constructor calls createGUI() automatically, so myScene is automatically initialized to some scene.
 * @author Austin Kao
 */
public class GUISetup implements FrontInternal{
    private static final String DEFAULT_RESOURCE = "resources/ViewDefaults";

    private Scene myScene;
    private Group root;
    private Console myConsole;
    private ResourceBundle myConstants;
    private ButtonManager buttonManager;


    public GUISetup() {
        myConstants = ResourceBundle.getBundle(DEFAULT_RESOURCE);
        buttonManager = new ButtonManager();
        myScene = createGUI(800,800, Color.AZURE);
    }

    public Scene createGUI(int width, int height, Paint background) {
        root = new Group();
        var scene = new Scene(root, width, height, background);
        myConsole = new Console();
        myConsole.getConsoleBox().setLayoutX(50);
        myConsole.getConsoleBox().setLayoutY(400);
        root.getChildren().addAll(buttonManager.getTurtleDisplay(), myConsole.getConsoleBox(), buttonManager.getUserOptions());
        return scene;
    }

    //External API maybe
    public Scene getScene() {
        return myScene;
    }

    public Label createLabel(String text) {
        Label createdLabel = new Label(text);
        return createdLabel;
    }

    public Button createButton(String title) {
        Button createdButton = new Button(title);
        return createdButton;
    }

    public ResourceBundle getDefaultValues() {
        return myConstants;
    }
}
