package view.button;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * This class controls how users will access a reference of all the SLogo commands.
 * May change to something that is completely encapsulated within the GUI.
 * @author Austin Kao
 */
public class HelpButton extends SLogoButton{
    private Stage myStage;

    public HelpButton(String label) {
        super(label);
        myStage = new Stage();
    }

    @Override
    public void processCommand() {
        WebView web = new WebView();
        var root = new Group();
        root.getChildren().add(web);
        Scene scene = new Scene(root);
        WebEngine webEngine = web.getEngine();
        webEngine.load("https://www2.cs.duke.edu/courses/compsci308/fall18/assign/03_slogo/commands.php#gsc.tab=0");
        myStage.setScene(scene);
        myStage.show();
    }
}
