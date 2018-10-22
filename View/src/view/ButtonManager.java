package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ResourceBundle;

public class ButtonManager {
    private static final String DEFAULT_RESOURCE = "resources/ViewDefaults";
    private static final String RESOURCE_PATH = "data/images/";
    private static final String IMAGE_PATH = "/images/";

    private TurtleDisplay turtleDisplay;
    private VBox userOptions;
    private ResourceBundle myConstants;


    public ButtonManager(){
        myConstants = ResourceBundle.getBundle(DEFAULT_RESOURCE);
        createUserOptions();
    }

    private void createUserOptions(){
        turtleDisplay = new TurtleDisplay();
        turtleDisplay.getCanvas().setVisible(true);
        ColorPicker colorPicker1 = new ColorPicker(Color.RED);
        Label penTitle = createLabel("Pen Color:");
        colorPicker1.setOnAction(event -> turtleDisplay.setPenColor(colorPicker1.getValue()));
        ColorPicker colorPicker2 = new ColorPicker();
        Label bgTitle = createLabel("Background Color:");
        colorPicker2.setOnAction(event -> turtleDisplay.setBgColor(colorPicker2.getValue()));
        Label languageTitle = createLabel("Languages:");
        LanguageMenu langMenu = new LanguageMenu();
        Button playPauseButton = createButton("Pause");
        playPauseButton.setOnAction(e -> playPauseAnimation(playPauseButton));
        Button stopButton = createButton("Stop");
        stopButton.setOnAction(e -> stopAnimation());
        Button changeButton = createButton("Change the turtle");
        changeButton.setOnAction(e -> chooseNewTurtle());
        Button helpButton = createButton("Help");
        helpButton.setOnAction(e -> openHelpPage(new Stage()));
        userOptions = new VBox(languageTitle, langMenu.getChoiceBox(),
                playPauseButton, stopButton, changeButton,helpButton, penTitle, colorPicker1, bgTitle, colorPicker2);
        userOptions.setSpacing(Double.parseDouble(myConstants.getString("defaultSpacing")));
        userOptions.setLayoutX(500);
        userOptions.setLayoutY(50);
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

    private void chooseNewTurtle() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File defaultFile = new File(RESOURCE_PATH);
        fileChooser.setInitialDirectory(defaultFile);
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try {
                turtleDisplay.getMyTurtle().setView(IMAGE_PATH + file.getName());
            } catch (Exception ex) {
                new ErrorAlert(ex);
            }
        }
    }

    private void playPauseAnimation(Button button) {
        if(button.getText().equals("Play")) {
            turtleDisplay.getCurrentAnimation().play();
            button.setText("Pause");
        } else {
            turtleDisplay.getCurrentAnimation().pause();
            button.setText("Play");
        }
    }

    //Potentially change method so that turtle resets to beginning of command
    private void stopAnimation() {
        turtleDisplay.getCurrentAnimation().stop();
    }

    private void openHelpPage(Stage stage) {
        WebView web = new WebView();
        WebEngine webEngine = web.getEngine();
        webEngine.load("https://www2.cs.duke.edu/courses/fall18/compsci308/assign/03_slogo/part2_PZ1.php#gsc.tab=0");
        Scene scene = new Scene(web);
        stage.setScene(scene);
    }

    public TurtleDisplay getTurtleDisplay() {
        return turtleDisplay;
    }

}
