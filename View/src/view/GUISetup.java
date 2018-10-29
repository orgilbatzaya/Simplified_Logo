package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
 * @author Austin Kao, Orgil Batzaya
 */
public class GUISetup implements FrontExternal, ViewResourceBundles {
    private static final int GUI_WIDTH = 1200;
    private static final int GUI_HEIGHT = 800;
    private static final double CANVAS_WIDTH = 400;
    private static final double CANVAS_HEIGHT = 400;
    private static final double CONSOLE_LAYOUT_X = 50;
    private static final double CONSOLE_LAYOUT_Y = 400;
    private static final double OPTIONS_LAYOUT_X = 500;
    private static final double OPTIONS_LAYOUT_Y = 50;
    private static final double INFO_LAYOUT_X = 800;
    private static final double INFO_LAYOUT_Y = 50;
    private static final double TURTLE_INFO_HEIGHT = 100;
    private static final String PEN_LABEL = "pen";
    private static final String LANGUAGE_LABEL = "language";
    private static final String BACKGROUND_LABEL = "background";
    private static final String TURTLE_CHOOSER_LABEL = "turtleChoose";
    private static final String PAUSE_LABEL = "pause";
    private static final String HELP_LABEL = "help";
    private static final String CHANGE_TURTLE_LABEL = "turtleChange";
    private static final String[] DIRECTIONS = {"Up","Down","Left","Right"};
    private static final String TURTLE_INFO_LABEL = "turtleInfo";
    private static final String SPACING = "defaultSpacing";
    private static final Color PEN_COLOR = Color.RED;
    private static final Color BACKGROUND_COLOR = Color.WHITE;

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
        myScene = createGUI(GUI_WIDTH,GUI_HEIGHT, Color.AZURE);
    }

    public Scene createGUI(int width, int height, Paint background) {
        root = new Group();
        var scene = new Scene(root, width, height, background);

        root.getChildren().add(makeTabs());
        return scene;
    }

    public Pane makePane(){
        myConsole = new Console(CONSOLE_LAYOUT_X, CONSOLE_LAYOUT_Y, this);
        myDisplays = new ArrayList<>();
        currentDisplay = new TurtleDisplay(CANVAS_WIDTH, CANVAS_HEIGHT);
        myDisplays.add(currentDisplay);

        PenColor penColor = new PenColor(PEN_COLOR, getDefault(PEN_LABEL), currentDisplay);
        BackgroundColor backgroundColor = new BackgroundColor(BACKGROUND_COLOR, getDefault(BACKGROUND_LABEL), currentDisplay);
        myLanguageMenu = new LanguageMenu(getDefault(LANGUAGE_LABEL));
        mySelector = new TurtleSelector(getDefault(TURTLE_CHOOSER_LABEL),currentDisplay);
        PlayPauseButton playPause = new PlayPauseButton(getDefault(PAUSE_LABEL), currentDisplay);
        ImageChooseButton changeTurtle = new ImageChooseButton(getDefault(CHANGE_TURTLE_LABEL), currentDisplay);
        HelpButton help = new HelpButton(getDefault(HELP_LABEL));


        VBox userOptions = new VBox(currentDisplay.getDurationDisplay(), myLanguageMenu.getDisplay(), playPause.getDisplay(), changeTurtle.getDisplay() ,
                help.getDisplay(), penColor.getDisplay(), backgroundColor.getDisplay(),mySelector.getDisplay(), createDirectionButtons());
        userOptions.setSpacing(getDefaultDouble(SPACING));
        userOptions.setLayoutX(OPTIONS_LAYOUT_X);
        userOptions.setLayoutY(OPTIONS_LAYOUT_Y);

        turtleInfo = new StatusDisplay(TURTLE_INFO_HEIGHT, getDefault(TURTLE_INFO_LABEL), currentDisplay);
        turtleInfo.getDisplay().setLayoutX(INFO_LAYOUT_X);
        turtleInfo.getDisplay().setLayoutY(INFO_LAYOUT_Y);
        Pane pane = new Pane();
        pane.getChildren().addAll(currentDisplay, myConsole.getConsoleBox(), userOptions, turtleInfo.getDisplay());
        return pane;

    }

    public TabPane makeTabs(){
        TabPane tabPane = new TabPane();
        Tab tab = new Tab("first");
        Pane p1 = makePane();
        Pane p2 = makePane();
        tab.setContent(p1);
        Tab t = new Tab("second");
        t.setContent(p2);
        tabPane.getTabs().addAll(tab,t);
        return tabPane;
    }

    public HBox createDirectionButtons(){
        HBox directions = new HBox();;
        for(int i = 0; i < DIRECTIONS.length; i++){
            directions.getChildren().add(new DirectionButton(DIRECTIONS[i],myConsole).getDisplay());
        }
        directions.setSpacing(getDefaultDouble(SPACING));
        return directions;
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
        for(int i = 0; i<currentDisplay.getTurtles().size(); i++) {
            Double[] valueElements = {currentDisplay.getTurtles().get(i).getHeading(),
                    currentDisplay.getTurtles().get(i).getX(),
                    currentDisplay.getTurtles().get(i).getY(),
                    DEFAULT_PEN, DEFAULT_VISIBLE, INITIAL_DISTANCE_MOVED,
                    (double) currentDisplay.getTurtles().get(i).getMyID(),
                    (double) (currentDisplay.getTurtles().get(i).isActive()?1:0)};
            for (int j = 0; j < keyElements.length; j++) {
                mapOut.put(keyElements[i], valueElements[i]);
            }
        }
        return mapOut;
    }

    public ResourceBundle getLanguage() {
        return myLanguageMenu.getLanguage();
    }

    public TurtleDisplay getCurrentDisplay() {
        return currentDisplay;
    }

    public StatusDisplay getTurtleInfoDisplay() {
        return turtleInfo;
    }
}
