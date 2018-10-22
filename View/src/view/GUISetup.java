package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashMap;
import java.util.Map;
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

    //for turtle parameter map
    public static final String HEADING_KEY = "heading";
    public static final String X_KEY = "xPos";
    public static final String Y_KEY = "yPos";
    public static final String DISTANCE_MOVED_KEY = "distanceMoved";
    public static final String PEN_KEY = "pen";
    public static final String VISIBLE_KEY= "visible";

    public static final double DEFAULT_PEN = 1;
    public static final double DEFAULT_VISIBLE = 1;
    public static final double INITIAL_DISTANCE_MOVED = 0;


    public GUISetup() {
        myConstants = ResourceBundle.getBundle(DEFAULT_RESOURCE);
        buttonManager = new ButtonManager();
        myScene = createGUI(800,800, Color.AZURE);
    }

    public Scene createGUI(int width, int height, Paint background) {
        root = new Group();
        var scene = new Scene(root, width, height, background);

        myConsole = new Console(this);
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

    public Console getConsole(){return myConsole; }

    public Map<String,Double> getTurtleParams(){
        HashMap<String,Double> mapOut = new HashMap<>();
        String[] keyElements = {HEADING_KEY,X_KEY,Y_KEY,PEN_KEY,VISIBLE_KEY,DISTANCE_MOVED_KEY};
        Double[] valueElements = {buttonManager.getTurtleDisplay().getMyTurtle().getHeading(),
                buttonManager.getTurtleDisplay().getMyTurtle().getX(),
                buttonManager.getTurtleDisplay().getMyTurtle().getY(),
                DEFAULT_PEN,DEFAULT_VISIBLE,INITIAL_DISTANCE_MOVED};
        for(int i = 0; i<keyElements.length;i++){
            mapOut.put(keyElements[i],valueElements[i]);
        }
        return mapOut;
    }

    public ButtonManager getButtonManager(){
        return buttonManager;
    }
}
