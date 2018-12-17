package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ErrorAlert;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TurtleImageSelector  {
    private static final String RESOURCE_PATH = "data/images/";
    private static final String TITLE = "Open File";
    private static final String IMAGE_PATH = "/images/";
    private HBox myBox;
    private List<ImageView> myTurtleViews;
    private TurtleDisplay td;




    public TurtleImageSelector(TurtleDisplay td){
        myBox = new HBox(5);
        myBox.setLayoutX(800);
        myBox.setLayoutY(300);
        myTurtleViews = new ArrayList<>();
        this.td= td;
        initializeViews();
    }

    private void initializeViews(){
        Map<Integer, TurtleView> turtles = td.getTurtles();
        for(int i = 0; i < turtles.size(); i++){
            Image image = turtles.get(i).getView().getImage();
            ImageView copy = new ImageView(image);
            copy.setPreserveRatio(true);
            copy.setFitHeight(40);
            final int id = i;
            myBox.getChildren().add(copy);
            myTurtleViews.add(copy);
            copy.setOnMouseClicked(e -> changeImage(id));
        }
    }

    private void changeImage(int id){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(TITLE);
        File defaultFile = new File(RESOURCE_PATH);
        fileChooser.setInitialDirectory(defaultFile);
        File file = fileChooser.showOpenDialog(new Stage());
        try{
            myTurtleViews.get(id).setImage(new Image(file.toURI().toString()));
            td.getTurtles().get(id).getView().setImage(new Image(file.toURI().toString()));
        }catch (Exception e){
            new ErrorAlert(e);
        }

    }


    public HBox getHBox(){
        return myBox;
    }
}
