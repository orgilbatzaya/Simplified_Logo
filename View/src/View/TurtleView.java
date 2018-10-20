package View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class TurtleView{
    private static final String DEFAULT_STARTING_TURTLE = "/resources/images/turtle-basic.png";
    private static final int DEFAULT_STARTING_POS = 0;

    private double mySpeed;

    private Color penColor;
    private boolean penDown;
    private double penWidth;
    private boolean visible;
    private ImageView myView;
    private int xPos;
    private int yPos;


    public TurtleView(){
        myView = new ImageView();
        Image turtleImage = new Image(this.getClass().getResourceAsStream(DEFAULT_STARTING_TURTLE));
        myView.setImage(turtleImage);
        xPos = DEFAULT_STARTING_POS;
        yPos = DEFAULT_STARTING_POS;
        myView.setX(xPos);
        myView.setY(yPos);
        myView.setFitHeight(30);
        myView.setFitWidth(30);

    }

    public ImageView getView(){
        return myView;
    }

    public int getX(){
        return xPos;
    }

    public int getY(){
        return yPos;
    }

    public void setX(int x){
        xPos = x;
    }
    
    public void setY(int y){
        xPos = y;
    }


}
