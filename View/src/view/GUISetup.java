package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
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
import view.dropdown.TurtleSelector;
import view.environmentdisplays.StatusDisplay;

import java.util.*;

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
    private static final double CONSOLE_LAYOUT_Y = 450;
    private static final double OPTIONS_LAYOUT_X = 500;
    private static final double OPTIONS_LAYOUT_Y = 50;
    private static final String PEN_LABEL = "pen";
    private static final String LANGUAGE_LABEL = "language";
    private static final String BACKGROUND_LABEL = "background";
    private static final String TURTLE_CHOOSER_LABEL = "turtleChoose";
    private static final String PAUSE_LABEL = "pause";
    private static final String HELP_LABEL = "help";
    private static final String CHANGE_TURTLE_LABEL = "turtleChange";
    private static final String[] DIRECTIONS = {"Up","Down","Left","Right"};
    private static final String SPACING = "defaultSpacing";
    private static final Color PEN_COLOR = Color.RED;
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final int NUM_DEFAULT_TABS = 5;

    private Scene myScene;
    private Group root;
    private Console myConsole;
    private TurtleDisplay currentD;
    private ArrayList<TurtleDisplay> myDisplays;
    private LanguageMenu myLanguageMenu;
    private TurtleSelector mySelector;

    public GUISetup() {
        myDisplays = new ArrayList<>();
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
        TurtleDisplay currentDisplay = new TurtleDisplay(CANVAS_WIDTH, CANVAS_HEIGHT);

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

        Pane pane = new Pane();
        pane.getChildren().addAll(currentDisplay, myConsole.getConsoleBox(), userOptions, currentDisplay.getTurtleInfoDisplay().getDisplay());
        myDisplays.add(currentDisplay);

        return pane;

    }

    public TabPane makeTabs(){
        TabPane tabPane = new TabPane();

        for(int i = 0; i < NUM_DEFAULT_TABS; i++){
            Tab t = new Tab(Integer.toString(i));
            Pane p = makePane();
            t.setContent(p);
            final int index = i;
            t.setOnSelectionChanged(event -> currentD = myDisplays.get(index));
            tabPane.getTabs().add(t);
        }
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

    public Scene getScene() {
        return myScene;
    }


    public VBox getDisplay() {
        return null;
    }

    public Console getConsole(){return myConsole; }

    public ResourceBundle getLanguage() {
        return myLanguageMenu.getLanguage();
    }

    public TurtleDisplay getCurrentDisplay() {
        return currentD;
    }

}
