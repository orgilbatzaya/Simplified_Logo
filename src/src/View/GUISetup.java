package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class sets up the GUI for SLogo. The method that creates most of the GUI is createGUI().
 * The default constructor calls createGUI() automatically, so myScene is automatically initialized to some scene.
 * @author Austin Kao
 */
public class GUISetup implements FrontInternal{
    private static final String[] LANGUAGE_ARRAY = {"Chinese","English", "French", "German", "Italian", "Portuguese", "Russian", "Spanish"};

    private Scene myScene;
    private GridPane myPane;
    private TurtleDisplay turtleDisplay;
    private Group root;
    private Console myConsole;

    public GUISetup() {
        myScene = createGUI(800,800, Color.AZURE);
    }

    public Scene createGUI(int width, int height, Paint background) {
        root = new Group();
        var scene = new Scene(root, width, height, background);
        turtleDisplay = new TurtleDisplay();
        myConsole = new Console();
        myConsole.getConsoleBox().setLayoutX(200);
        myConsole.getConsoleBox().setLayoutY(400);
        ArrayList<String> languages = new ArrayList<>();
        languages.add("English");
        languages.add("Spanish");
        Label languageTitle = createLabel("Languages:");
        LanguageMenu langMenu = new LanguageMenu(languages);
        ArrayList<String> colors = new ArrayList<>();
        colors.add("Black");
        colors.add("Red");
        Label colorTitle = createLabel("Colors:");
        ColorMenu colorMenu = new ColorMenu(colors);
        Button startButton = createButton("Start");
        Button stopButton = createButton("Stop");
        VBox userOptions = new VBox(languageTitle, langMenu.getChoiceBox(), colorTitle, colorMenu.getChoiceBox(),startButton,stopButton);
        userOptions.setSpacing(5);
        userOptions.setLayoutX(500);
        userOptions.setLayoutY(100);
        root.getChildren().addAll(turtleDisplay.getCanvas(), myConsole.getConsoleBox(), userOptions);
        scene.setOnKeyPressed(event -> myConsole.processCommand());
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
        try {
            for(int i = 0; i < LANGUAGE_ARRAY.length; i++) {
            }
            return languages;
        } catch (Exception e) {
            System.out.println("hi");
            return languages;
            /*catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            System.out.println("Cannot get languages.");
            return languages;
        }*/
        }
    }
}
