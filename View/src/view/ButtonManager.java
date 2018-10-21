package view;

import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ResourceBundle;

public class ButtonManager {
    TurtleDisplay turtleDisplay;
    VBox userOptions;
    private ResourceBundle myConstants;
    private static final String DEFAULT_RESOURCE = "resources/ViewDefaults";


    public ButtonManager(TurtleDisplay turtleDisplay){
        this.turtleDisplay = turtleDisplay;
        myConstants = ResourceBundle.getBundle(DEFAULT_RESOURCE);
        createUserOptions();

    }

    private void createUserOptions(){
        ColorPicker colorPicker1 = new ColorPicker(Color.RED);
        Label penTitle = createLabel("Pen Color:");
        colorPicker1.setOnAction(event ->  {
            turtleDisplay.setPenColor(colorPicker1.getValue());
        });
        ColorPicker colorPicker2 = new ColorPicker();
        Label bgTitle = createLabel("Background Color:");
        colorPicker2.setOnAction(event ->  {
            turtleDisplay.setBgColor(colorPicker2.getValue());
        });
        Label languageTitle = createLabel("Languages:");
        LanguageMenu langMenu = new LanguageMenu();
        Button startButton = createButton("Start");
        Button stopButton = createButton("Stop");
        userOptions = new VBox(languageTitle, langMenu.getChoiceBox(),
                startButton, stopButton, penTitle, colorPicker1, bgTitle, colorPicker2);
        userOptions.setSpacing(Double.parseDouble(myConstants.getString("defaultSpacing")));
        userOptions.setLayoutX(500);
        userOptions.setLayoutY(100);
    }

    public VBox getUserOptions(){
        return userOptions;
    }

    private Label createLabel(String text) {
        Label createdLabel = new Label(text);
        return createdLabel;
    }

    public Button createButton(String title) {
        Button createdButton = new Button(title);
        return createdButton;
    }



}
