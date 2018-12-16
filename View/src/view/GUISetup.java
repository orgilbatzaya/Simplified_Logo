package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import view.button.*;
import view.colorpicker.BackgroundColor;
import view.colorpicker.PenColor;
import view.colorpicker.SLogoColor;
import view.dropdown.LanguageMenu;
import view.dropdown.TurtleSelector;
import view.fields.DurationField;
import view.fields.Field;
import view.flowpane.TurtleImageChooser;

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
    private static final String DURATION_LABEL = "duration";
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
    private TurtleImageChooser myImageChooser;

    public GUISetup() {
        myDisplays = new ArrayList<>();
        myScene = createGUI(GUI_WIDTH,GUI_HEIGHT, Color.AZURE);
    }

    public Scene createGUI(int width, int height, Paint background) {
        root = new Group();
        var scene = new Scene(root, width, height, background);
        root.getChildren().add(makeTabs());
        myConsole = new Console(CONSOLE_LAYOUT_X, CONSOLE_LAYOUT_Y, this);
        var userOptionsMenu = makeUserOptions();
        Label imageChooseLabel = new Label(getDefault(CHANGE_TURTLE_LABEL));
        imageChooseLabel.setLayoutX(900);
        imageChooseLabel.setLayoutY(200);
        myImageChooser = new TurtleImageChooser(5, 5, 900, 220,this);
        root.getChildren().addAll(myConsole.getConsoleBox(), userOptionsMenu, imageChooseLabel, myImageChooser);
        return scene;
    }

    public VBox makeUserOptions() {
        SLogoColor penColor = new PenColor(PEN_COLOR, getDefault(PEN_LABEL), this);
        SLogoColor backgroundColor = new BackgroundColor(BACKGROUND_COLOR, getDefault(BACKGROUND_LABEL), this);
        myLanguageMenu = new LanguageMenu(getDefault(LANGUAGE_LABEL));
        Field animationDuration = new DurationField(myDefaults.getString(DURATION_LABEL), this);
        SLogoButton playPause = new PlayPauseButton(getDefault(PAUSE_LABEL), this);
        SLogoButton changeTurtle = new ImageChooseButton(getDefault(CHANGE_TURTLE_LABEL), this);
        SLogoButton help = new HelpButton(getDefault(HELP_LABEL));
        VBox userOptions = new VBox(animationDuration.getDisplay(), myLanguageMenu.getDisplay(), playPause.getDisplay(), changeTurtle.getDisplay() ,
                help.getDisplay(), penColor.getDisplay(), backgroundColor.getDisplay(),mySelector.getDisplay(), createDirectionButtons());
        userOptions.setSpacing(getDefaultDouble(SPACING));
        userOptions.setLayoutX(OPTIONS_LAYOUT_X);
        userOptions.setLayoutY(OPTIONS_LAYOUT_Y);
        return userOptions;
    }

    public TurtleDisplay makePane(){
        TurtleDisplay currentDisplay = new TurtleDisplay(CANVAS_WIDTH, CANVAS_HEIGHT);
        mySelector = new TurtleSelector(getDefault(TURTLE_CHOOSER_LABEL),currentDisplay);
        Pane pane = new Pane();
        pane.getChildren().addAll(currentDisplay, currentDisplay.getTurtleInfoDisplay().getDisplay());
        myDisplays.add(currentDisplay);
        return currentDisplay;

    }

    public TabPane makeTabs(){
        TabPane tabPane = new TabPane();

        for(int i = 0; i < NUM_DEFAULT_TABS; i++){
            Tab t = new Tab(Integer.toString(i));
            TurtleDisplay td = makePane();
            t.setContent(td);
            t.setOnSelectionChanged(event -> updateTab(td));
            tabPane.getTabs().add(t);
        }
        return tabPane;
    }

    private void updateTab(TurtleDisplay display) {
        currentD = display;
        if(myImageChooser != null) {
            myImageChooser.update();
        }
    }


    public HBox createDirectionButtons(){
        HBox directions = new HBox();
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
