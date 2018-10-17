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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

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
        //GridPane gp = createPane("Hi");
        //root.getChildren().add(gp);
        turtleDisplay = new TurtleDisplay();
        myConsole = new Console();
        myConsole.getConsoleBox().setPadding(new Insets(400,400,100,100));
        root.getChildren().add(turtleDisplay.getCanvas());
        root.getChildren().add(myConsole.getConsoleBox());
        ArrayList<String> languages = new ArrayList<>();
        languages.add("English");
        VBox languageChoiceBox = createChoiceBox("Languages:", languages, 450, 100);
        root.getChildren().add(languageChoiceBox);
        ArrayList<String> colors = new ArrayList<>();
        colors.add("Black");
        VBox colorChoiceBox = createChoiceBox("Colors:", colors, 450, 200);
        root.getChildren().add(colorChoiceBox);
        Button startButton = createButton("Start", 600, 200);
        Button stopButton = createButton("Stop", 600, 300);
        Button resetButton = createButton("Reset", 600, 400);

        root.getChildren().addAll(startButton, stopButton, resetButton);
        scene.setOnKeyPressed(event -> myConsole.processCommand());


        return scene;
    }

    //External API maybe
    public Scene getScene() {
        return myScene;
    }

    public GridPane createPane(String text) {
        GridPane gp = new GridPane();
        Label gpLabel = new Label();
        gpLabel.setText(text);
        gp.add(gpLabel, 0 , 0);
        return gp;
    }

    public VBox createChoiceBox(String title, ArrayList<String> items, double xPos, double yPos) {
        VBox choiceVBox = new VBox();
        Text vboxTitle = new Text(title);
        ChoiceBox<String> createdBox = new ChoiceBox<>();
        createdBox.setItems(FXCollections.observableArrayList(items));
        choiceVBox.getChildren().addAll(vboxTitle, createdBox);
        choiceVBox.setLayoutX(xPos);
        choiceVBox.setLayoutY(yPos);
        return choiceVBox;
    }

    public Button createButton(String title, double xPos, double yPos) {
        Button createdButton = new Button(title);
        createdButton.setLayoutX(xPos);
        createdButton.setLayoutY(yPos);
        return createdButton;
    }
}
