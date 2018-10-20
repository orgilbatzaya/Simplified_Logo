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

import java.util.ArrayList;

/**
 * This class sets up the GUI for SLogo. The method that creates most of the GUI is createGUI().
 * The default constructor calls createGUI() automatically, so myScene is automatically initialized to some scene.
 * @author Austin Kao
 */
public class GUISetup implements FrontInternal{

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
        turtleDisplay.getCanvas().setVisible(true);


        ColorPicker colorPicker1 = new ColorPicker(Color.RED);
        HBox penTitle = createLabel("Pen Color:");
        colorPicker1.setOnAction(event ->  {
                turtleDisplay.setPenColor(colorPicker1.getValue());
        });
        ColorPicker colorPicker2 = new ColorPicker(Color.WHITE);
        HBox bgTitle = createLabel("Background Color:");
        colorPicker2.setOnAction(event ->  {
            turtleDisplay.setBgColor(colorPicker2.getValue());
        });

        myConsole = new Console();
        myConsole.getConsoleBox().setLayoutX(200);
        myConsole.getConsoleBox().setLayoutY(400);
        root.getChildren().add(turtleDisplay);
        root.getChildren().add(myConsole.getConsoleBox());

        ArrayList<String> languages = new ArrayList<>();
        languages.add("English");
        languages.add("Spanish");
        HBox languageTitle = createLabel("Languages:");
        LanguageMenu langMenu = new LanguageMenu(languages);
        ArrayList<String> colors = new ArrayList<>();
        colors.add("Black");
        colors.add("Red");
        HBox colorTitle = createLabel("Colors:");
        LanguageMenu colorMenu = new LanguageMenu(colors);
        Button startButton = createButton("Start");
        Button stopButton = createButton("Stop");
        VBox userOptions = new VBox(languageTitle, langMenu.getChoiceBox(), colorTitle, colorMenu.getChoiceBox(),
                startButton, stopButton, penTitle, colorPicker1, bgTitle, colorPicker2);
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



    public Button createButton(String title) {
        Button createdButton = new Button(title);
        return createdButton;
    }

    private void createLanguageList() {

    }

    private void changeColor(String color) {
        myScene.setFill(Paint.valueOf(color.toUpperCase()));
    }


}