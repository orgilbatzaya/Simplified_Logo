package view.flowpane;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.GUISetup;
import view.TurtleView;

import java.io.File;
import java.util.Map;

public class TurtleImageChooser extends SLogoFlowPane {


    private GUISetup myParentGUI;
    private Map<Integer, TurtleView> myTurtles;

    public TurtleImageChooser(double hGap, double vGap, double x, double y, GUISetup gui) {
        super(hGap, vGap, x, y);
        myParentGUI = gui;
        update();
    }

    public void update() {
        getChildren().clear();
        myTurtles = myParentGUI.getCurrentDisplay().getTurtles();
        for(Integer i : myTurtles.keySet()) {
            TurtleView tv = myTurtles.get(i);
            ImageView paneView = new ImageView(tv.getView().getImage());
            paneView.setFitWidth(20);
            paneView.setFitHeight(20);
            paneView.setOnMouseClicked(e -> {
                File file = tv.handle(e);
                myParentGUI.getCurrentDisplay().getTurtles().get(i).changeView(file);
                paneView.setImage(new Image(file.toURI().toString()));
            });
            this.getChildren().add(paneView);
        }
    }
}
