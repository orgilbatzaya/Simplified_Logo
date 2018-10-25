package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import view.button.HelpButton;
import view.button.ImageChooseButton;
import view.button.PlayPauseButton;
import view.colorpicker.BackgroundColor;
import view.colorpicker.PenColor;
import view.dropdown.LanguageMenu;

import java.util.ArrayList;
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
    private TurtleDisplay currentDisplay;
    private ArrayList<TurtleDisplay> myDisplays;
    private LanguageMenu myLanguageMenu;

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
        myScene = createGUI(800,800, Color.AZURE);
    }

    public Scene createGUI(int width, int height, Paint background) {
        root = new Group();
        var scene = new Scene(root, width, height, background);
        myConsole = new Console(this);
        myConsole.getConsoleBox().setLayoutX(50);
        myConsole.getConsoleBox().setLayoutY(400);
        myDisplays = new ArrayList<>();
        currentDisplay = new TurtleDisplay();
        myDisplays.add(currentDisplay);
        PenColor penColor = new PenColor(Color.RED, "Pen Color:", currentDisplay);
        BackgroundColor backgroundColor = new BackgroundColor(Color.WHITE, "Background Color:", currentDisplay);
        myLanguageMenu = new LanguageMenu("Languages:");
        PlayPauseButton playPause = new PlayPauseButton("Pause", currentDisplay);
        ImageChooseButton changeTurtle = new ImageChooseButton("Change the turtle", currentDisplay.getMyTurtle());
        HelpButton help = new HelpButton("Help");
        VBox userOptions = new VBox(currentDisplay.getDurationDisplay(), myLanguageMenu.getDisplay(), playPause.getDisplay(), changeTurtle.getDisplay() ,
                help.getDisplay(), penColor.getDisplay(), backgroundColor.getDisplay());
        userOptions.setSpacing(Double.parseDouble(myConstants.getString("defaultSpacing")));
        userOptions.setLayoutX(500);
        userOptions.setLayoutY(50);
        root.getChildren().addAll(currentDisplay, myConsole.getConsoleBox(), userOptions);
        return scene;
    }

    //External API maybe
    public Scene getScene() {
        return myScene;
    }

    public VBox getDisplay() {
        return null;
    }

    public ResourceBundle getDefaultValues() {
        return myConstants;
    }

    public Console getConsole(){return myConsole; }

    public Map<String,Double> getTurtleParams(){
        HashMap<String,Double> mapOut = new HashMap<>();
        String[] keyElements = {HEADING_KEY,X_KEY,Y_KEY,PEN_KEY,VISIBLE_KEY,DISTANCE_MOVED_KEY};
        Double[] valueElements = {currentDisplay.getMyTurtle().getHeading(),
                currentDisplay.getMyTurtle().getX(),
                currentDisplay.getMyTurtle().getY(),
                DEFAULT_PEN,DEFAULT_VISIBLE,INITIAL_DISTANCE_MOVED};
        for(int i = 0; i<keyElements.length;i++){
            mapOut.put(keyElements[i],valueElements[i]);
        }
        return mapOut;
    }

    public ResourceBundle getLanguage() {
        return myLanguageMenu.getLanguage();
    }

    public TurtleDisplay getCurrentDisplay() {
        return currentDisplay;
    }
}
