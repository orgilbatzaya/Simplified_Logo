package view;

import javafx.beans.Observable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class TurtleView {
    private static final String DEFAULT_STARTING_TURTLE = "turtle-basic.png";
    private static final int DEFAULT_STARTING_POS = 0;
    private static final Color DEFAULT_PEN_COLOR = Color.RED;
    private static final double DEFAULT_PEN_WIDTH = 5;

    private double mySpeed;
    private Color penColor;
    private boolean penDown;
    private double penWidth;
    private boolean visible;
    private ImageView myView;
    private int xPos;
    private int yPos;
    private Observable ov = null;

    public TurtleView(){
        myView = new ImageView();
        Image turtleImage = new Image(this.getClass().getClassLoader().getResourceAsStream(DEFAULT_STARTING_TURTLE));
        myView.setImage(turtleImage);
        xPos = DEFAULT_STARTING_POS;
        yPos = DEFAULT_STARTING_POS;
        myView.setX(xPos);
        myView.setY(yPos);
        myView.setFitHeight(30);
        myView.setFitWidth(30);
        setPenColor(DEFAULT_PEN_COLOR);
        setPenWidth(DEFAULT_PEN_WIDTH);
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

    public void setPenColor(Color c){
        penColor = c;
    }

    public Color getPenColor(){
        return penColor;
    }

    public void setPenWidth(double w){
        penWidth = w;
    }

    public double getPenWidth(){
        return penWidth;
    }

    public void move(int x, int y){

    }
    //@Override
    public void update(Observable item, Object obj){
        System.out.println(ov);
    }

}
