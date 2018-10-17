package View;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * This class sets up the GUI for SLogo. The method that creates most of the GUI is createGUI().
 * The default constructor calls createGUI() automatically, so myScene is automatically initialized to some scene.
 * @author Austin Kao
 */
public class GUISetup implements FrontInternal{
    private static final String DEFAULT_LANGUAGE_PACKAGE = "resources/languages/";

    private Scene myScene;
    private GridPane myPane;
    private TurtleDisplay turtleDisplay;
    private Group root;
    private Console myConsole;
    private ResourceBundle myLanguage;

    public GUISetup() {
        myScene = createGUI(800,800, Color.AZURE);
    }

    public Scene createGUI(int width, int height, Paint background) {
        root = new Group();
        var scene = new Scene(root, width, height, background);
        //GridPane gp = createPane("Hi");
        //root.getChildren().add(gp);
        turtleDisplay = new TurtleDisplay();
        myConsole = new Console();
        myConsole.getConsoleBox().setPadding(new Insets(400,400,100,100));
        root.getChildren().add(turtleDisplay.getPane());
        root.getChildren().add(myConsole.getConsoleBox());
        VBox userOptions = new VBox();
        ArrayList<String> languages = new ArrayList<>();
        languages.add("English");
        languages.add("Spanish");
        HBox languageTitle = createLabel("Languages:");
        ChoiceBox languageChoiceBox = createChoiceBox(languages);
        languageChoiceBox.setOnAction(e -> changeLanguage(languageChoiceBox.getValue().toString()));
        ArrayList<String> colors = new ArrayList<>();
        colors.add("Black");
        colors.add("Red");
        HBox colorTitle = createLabel("Colors:");
        ChoiceBox colorChoiceBox = createChoiceBox(colors);
        colorChoiceBox.setOnAction(e -> changeColor(colorChoiceBox.getValue().toString()));
        Button startButton = createButton("Start");
        Button stopButton = createButton("Stop");
        Button resetButton = createButton("Reset");
        userOptions.getChildren().addAll(languageTitle, languageChoiceBox, colorTitle, colorChoiceBox,startButton,stopButton,resetButton);
        userOptions.setSpacing(5);
        userOptions.setLayoutX(500);
        userOptions.setLayoutY(100);
        root.getChildren().add(userOptions);
        scene.setOnKeyPressed(event -> myConsole.processCommand());
        return scene;
    }

    //External API maybe
    public Scene getScene() {
        return myScene;
    }

    public HBox createLabel(String text) {
        Label createdLabel = new Label(text);
        HBox labelHBox = new HBox(createdLabel);
        return labelHBox;
    }

    public ChoiceBox createChoiceBox(ArrayList<String> items) {
        ChoiceBox<String> createdBox = new ChoiceBox<>();
        createdBox.setItems(FXCollections.observableArrayList(items));
        if(items.size() > 0) {
            createdBox.setValue(items.get(0));
        }
        return createdBox;
    }

    public Button createButton(String title) {
        Button createdButton = new Button(title);
        return createdButton;
    }

    private void changeLanguage(String language) {
        myLanguage = ResourceBundle.getBundle(DEFAULT_LANGUAGE_PACKAGE+language);
        System.out.println(myLanguage.getString("Forward"));
    }

    private void createLanguageList() {

    }

    private void changeColor(String color) {
        myScene.setFill(Paint.valueOf(color.toUpperCase()));
    }
}
