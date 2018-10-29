package view.button;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import view.ViewResourceBundles;

/**
 * This class controls how users will access a reference of all the SLogo commands.
 * May change to something that is completely encapsulated within the GUI.
 * @author Austin Kao
 */
public class HelpButton extends SLogoButton implements ViewResourceBundles {
    private static final String HELP_TITLE = "helpTitle";
    private static final String HELP_TEXT = "helpText";
    private static final String BASIC_TEXT = "basic";
    private static final String ADVANCED_TEXT = "advanced";
    private static final String BASIC_URL = "basicURL";
    private static final String ADVANCED_URL = "advancedURL";
    private static final String SPACING = "defaultMediumSpacing";

    private Stage myStage;
    private Scene myScene;
    private Group root;

    public HelpButton(String label) {
        super(label);
        myStage = new Stage();
        root = new Group();
        myScene = new Scene(root);
        myStage.setTitle(getDefault(HELP_TITLE));
    }

    @Override
    public void processCommand() {
        root.getChildren().clear();
        Text helpText = new Text(getDefault(HELP_TEXT));
        Button basicButton = new Button(getDefault(BASIC_TEXT));
        basicButton.setOnAction(e -> seeCommands(myDefaults.getString(BASIC_URL)));
        Button advancedButton = new Button(getDefault(ADVANCED_TEXT));
        advancedButton.setOnAction(e -> seeCommands(myDefaults.getString(ADVANCED_URL)));
        HBox buttonBox = new HBox(basicButton, advancedButton);
        buttonBox.setSpacing(getDefaultDouble(SPACING));
        VBox stageBox = new VBox(helpText, buttonBox);
        stageBox.setSpacing(getDefaultDouble(SPACING));
        root.getChildren().add(stageBox);
        myStage.setScene(myScene);
        myStage.show();
    }

    private void seeCommands(String url) {
        root.getChildren().clear();
        WebView web = new WebView();
        root.getChildren().add(web);
        WebEngine webEngine = web.getEngine();
        webEngine.load(url);
        myStage.setScene(myScene);
        myStage.show();
    }
}
