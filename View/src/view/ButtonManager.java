package view;

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
    TurtleDisplay turtleDisplay;
    VBox userOptions;
    private ResourceBundle myConstants;
    private static final String DEFAULT_RESOURCE = "resources/ViewDefaults";
    private Button playPauseButton;
    private static final String RESOURCE_PATH = "data/images/";
    private static final String IMAGE_PATH = "/images/";



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

        playPauseButton = createButton("Pause");
        playPauseButton.setOnAction(e -> playPauseAnimation());
        Button stopButton = createButton("Stop");
        stopButton.setOnAction(e -> stopAnimation());
        Button changeButton = createButton("Change the turtle");
        changeButton.setOnAction(e -> chooseNewTurtle());
        Button helpButton = createButton("Help");
        helpButton.setOnAction(e -> openHelpPage());
        userOptions = new VBox(languageTitle, langMenu.getChoiceBox(),
                playPauseButton, stopButton, changeButton,helpButton, penTitle, colorPicker1, bgTitle, colorPicker2);
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

    private void playPauseAnimation() {
        if(playPauseButton.getText().equals("Play")) {
            turtleDisplay.getCurrentAnimation().play();
            playPauseButton.setText("Pause");
        } else {
            turtleDisplay.getCurrentAnimation().pause();
            playPauseButton.setText("Play");
        }
    }

    //Potentially change method so that turtle resets to beginning of command
    private void stopAnimation() {
        turtleDisplay.getCurrentAnimation().stop();
    }

    private void openHelpPage() {
        WebView web = new WebView();
        WebEngine webEngine = web.getEngine();
        webEngine.load("https://www2.cs.duke.edu/courses/fall18/compsci308/assign/03_slogo/part2_PZ1.php#gsc.tab=0");
    }



}
