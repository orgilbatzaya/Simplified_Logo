package View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This class sets up the GUI for SLogo. The method that creates most of the GUI is createGUI().
 * The default constructor calls createGUI() automatically, so myScene is automatically initialized to some scene.
 * @author Austin Kao
 */
public class GUISetup implements FrontInternal{
    private static final String DEFAULT_RESOURCE = "resources/ViewDefaults";
    private static final String[] LANGUAGE_ARRAY = {"Chinese","English", "French", "German", "Italian", "Portuguese", "Russian", "Spanish"};

    private Scene myScene;
    private TurtleDisplay turtleDisplay;
    private Group root;
    private Console myConsole;
    private ResourceBundle myConstants;

    public GUISetup() {
        myScene = createGUI(800,800, Color.AZURE);
        myConstants = ResourceBundle.getBundle(DEFAULT_RESOURCE);
        //listLanguages();
    }

    public Scene createGUI(int width, int height, Paint background) {
        root = new Group();
        var scene = new Scene(root, width, height, background);
        turtleDisplay = new TurtleDisplay();
        turtleDisplay.getCanvas().setVisible(true);
        ColorPicker colorPicker1 = new ColorPicker(Color.RED);
        Label penTitle = createLabel("Pen Color:");
        colorPicker1.setOnAction(event ->  {
                turtleDisplay.setPenColor(colorPicker1.getValue());
        });
        ColorPicker colorPicker2 = new ColorPicker(Color.WHITE);
        Label bgTitle = createLabel("Background Color:");
        colorPicker2.setOnAction(event ->  {
            turtleDisplay.setBgColor(colorPicker2.getValue());
        });
        myConsole = new Console();
        myConsole.getConsoleBox().setLayoutX(200);
        myConsole.getConsoleBox().setLayoutY(400);
        ArrayList<String> languages = new ArrayList<>();
        languages.add("English");
        languages.add("Spanish");
        Label languageTitle = createLabel("Languages:");
        LanguageMenu langMenu = new LanguageMenu(languages);
        Button startButton = createButton("Start");
        Button stopButton = createButton("Stop");
        VBox userOptions = new VBox(languageTitle, langMenu.getChoiceBox(),
                startButton, stopButton, penTitle, colorPicker1, bgTitle, colorPicker2);
        //userOptions.setSpacing(Double.parseDouble(myConstants.getString("defaultSpacing")));
        userOptions.setLayoutX(500);
        userOptions.setLayoutY(100);
        root.getChildren().addAll(turtleDisplay.getCanvas(), myConsole.getConsoleBox(), userOptions);
        scene.setOnKeyPressed(event -> myConsole.processCommand(event.getCode()));
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

    private void changeColor(String color) {
        myScene.setFill(Paint.valueOf(color.toUpperCase()));
    }

    public ArrayList<String> listLanguages() {
        ArrayList<String> languages = new ArrayList<>();
        for(int i = 0; i < LANGUAGE_ARRAY.length; i++) {
            languages.add(LANGUAGE_ARRAY[i]);
            ResourceBundle myLanguage = ResourceBundle.getBundle("resources/languages/"+LANGUAGE_ARRAY[i]);
            System.out.println(myLanguage.getString("Forward"));
            System.out.println(myLanguage.getBaseBundleName());
        }
        return languages;
    }
}
