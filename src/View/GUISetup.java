package View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * This class sets up the GUI for SLogo. The method that creates most of the GUI is createGUI().
 * The default constructor calls createGUI() automatically, so myScene is automatically initialized to some scene.
 * @author Austin Kao
 */
public class GUISetup implements FrontInternal{
    private Scene myScene;
    private GridPane myPane;
    private Group root;

    public GUISetup() {
        myScene = createGUI(200,200, Color.AZURE);
    }

    @Override
    public Scene createGUI(int width, int height, Paint background) {
        root = new Group();
        var scene = new Scene(root, width, height, background);
        GridPane gp = createPane("Hi");
        root.getChildren().add(gp);
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
}
