package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import view.button.DirectionButton;
import view.button.HelpButton;
import view.button.ImageChooseButton;
import view.button.PlayPauseButton;
import view.colorpicker.BackgroundColor;
import view.colorpicker.PenColor;
import view.dropdown.LanguageMenu;
import view.environmentdisplays.StatusDisplay;
import view.dropdown.TurtleSelector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * This class sets up the GUI for SLogo. The method that creates most of the GUI is createGUI().
 * The default constructor calls createGUI() automatically, so myScene is automatically initialized to some scene.
 * @author Austin Kao
 */
public class GUISetup implements FrontExternal, ViewResourceBundles {
    private static final double CANVAS_WIDTH = 400;
    private static final double CANVAS_HEIGHT = 400;

    private Scene myScene;
    private Group root;
    private Console myConsole;
    private TurtleDisplay currentDisplay;
    private ArrayList<TurtleDisplay> myDisplays;
    private LanguageMenu myLanguageMenu;
    private StatusDisplay turtleInfo;
    private TurtleSelector mySelector;

    private static final double DEFAULT_PEN = 1;
    private static final double DEFAULT_VISIBLE = 1;
    private static final double INITIAL_DISTANCE_MOVED = 0;


    public GUISetup() {
        myScene = createGUI(1200,800, Color.AZURE);
    }

    public Scene createGUI(int width, int height, Paint background) {
        root = new Group();
        var scene = new Scene(root, width, height, background);
        myConsole = new Console(this);
        myConsole.getConsoleBox().setLayoutX(50);
        myConsole.getConsoleBox().setLayoutY(400);
        myDisplays = new ArrayList<>();
        currentDisplay = new TurtleDisplay(CANVAS_WIDTH, CANVAS_HEIGHT);
        myDisplays.add(currentDisplay);
        PenColor penColor = new PenColor(Color.RED, "Pen Color:", currentDisplay);
        BackgroundColor backgroundColor = new BackgroundColor(Color.PURPLE, "Background Color:", currentDisplay);
        myLanguageMenu = new LanguageMenu("Languages:");
        mySelector = new TurtleSelector("Choose turtle(s):",currentDisplay);
        PlayPauseButton playPause = new PlayPauseButton("Pause", currentDisplay);
        ImageChooseButton changeTurtle = new ImageChooseButton("Change the turtle", currentDisplay);
        HelpButton help = new HelpButton("Help");
        HBox directions = new HBox(new DirectionButton("Up",myConsole).getDisplay(),new DirectionButton("Down", myConsole).getDisplay(),
                                    new DirectionButton("Left", myConsole).getDisplay(), new DirectionButton("Right", myConsole).getDisplay());
        VBox userOptions = new VBox(currentDisplay.getDurationDisplay(), myLanguageMenu.getDisplay(), playPause.getDisplay(), changeTurtle.getDisplay() ,
                help.getDisplay(), penColor.getDisplay(), backgroundColor.getDisplay(),mySelector.getDisplay(), directions);
        userOptions.setSpacing(Double.parseDouble(myDefaults.getString("defaultSpacing")));
        userOptions.setLayoutX(500);
        userOptions.setLayoutY(50);
        turtleInfo = new StatusDisplay(100, "Turtle Info:", currentDisplay);
        turtleInfo.getDisplay().setLayoutX(700);
        turtleInfo.getDisplay().setLayoutY(50);
        root.getChildren().addAll(currentDisplay, myConsole.getConsoleBox(), userOptions, turtleInfo.getDisplay());
        return scene;
    }

    //External API maybe
    public Scene getScene() {
        return myScene;
    }

    public VBox getDisplay() {
        return null;
    }

    public Console getConsole(){return myConsole; }

    public Map<String,Double> getTurtleParams(){
        HashMap<String,Double> mapOut = new HashMap<>();
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
