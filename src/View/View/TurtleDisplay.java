package View;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;


public class TurtleDisplay {
    private GridPane myPane;
    private Label myLabel;
    private int[] myDim = {40,40};


    public TurtleDisplay(){
        myPane = new GridPane();
        myPane.setMinSize(200, 200);
        myPane.setPrefSize(400,400);
        myPane.setPadding(new Insets(10, 10, 10, 10));
        myPane.setVgap(0);
        myPane.setHgap(0);
        myPane.setAlignment(Pos.CENTER);
        myPane.setGridLinesVisible(true);
        addStyle();
        addStuff();
        rowsCols();

    }

    public GridPane getPane(){
        return myPane;
    }

    private void addStuff(){
        myLabel = new Label();
        myLabel.setText("hello");
        //myPane.add(myLabel, 3 , 3);
    }

    private void addStyle(){
        myPane.setStyle("-fx-border-color: black");
        myPane.setStyle("-fx-background-color: #ffffff;");


    }

    private void rowsCols() {
        for(int i = 0; i < myDim[0]; i++){
            RowConstraints row = new RowConstraints(10);
            myPane.getRowConstraints().add(row);
        }
       for(int i = 0 ; i < myDim[1]; i++){
           ColumnConstraints col = new ColumnConstraints(10);
           myPane.getColumnConstraints().add(col);
       }
       //myPane.setPadding(new Insets(60));

    }

}
