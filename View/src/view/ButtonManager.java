package view;

import javafx.event.Event;
import javafx.scene.Group;
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
    private LanguageMenu myLanguageMenu;


    public ButtonManager(){
        myConstants = ResourceBundle.getBundle(DEFAULT_RESOURCE);
        createUserOptions();
    }

    private void createUserOptions(){
        turtleDisplay = new TurtleDisplay();
        turtleDisplay.getCanvas().setVisible(true);
        PenColor penColor = new PenColor(Color.RED, "Pen Color:", turtleDisplay);
        BackgroundColor backgroundColor = new BackgroundColor(Color.WHITE, "Background Color:", turtleDisplay);
        myLanguageMenu = new LanguageMenu("Languages:");
        Button playPauseButton = new Button("Pause");
        playPauseButton.setOnAction(e -> playPauseAnimation(playPauseButton));
        Button stopButton = new Button("Stop");
        stopButton.setOnAction(e -> stopAnimation());
        Button changeButton = new Button("Change the turtle");
        changeButton.setOnAction(e -> chooseNewTurtle());
        Button helpButton = new Button("Help");
        helpButton.setOnAction(e -> openHelpPage(new Stage()));
        userOptions = new VBox(myLanguageMenu.getDisplay(), playPauseButton, stopButton, changeButton,helpButton, penColor.getDisplay(), backgroundColor.getDisplay());
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
        var root = new Group();
        root.getChildren().add(web);
        Scene scene = new Scene(root);
        WebEngine webEngine = web.getEngine();
        webEngine.load("https://www2.cs.duke.edu/courses/compsci308/fall18/assign/03_slogo/commands.php#gsc.tab=0");
        stage.setScene(scene);
        stage.show();
    }

    public TurtleDisplay getTurtleDisplay() {
        return turtleDisplay;
    }

    public ResourceBundle getLanguageFromUserOptions() {
        return myLanguageMenu.getLanguage();
    }

}
