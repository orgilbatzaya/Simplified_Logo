package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TurtleView {
    private static final String DEFAULT_STARTING_TURTLE = "/images/turtle-basic.png";
    private static final int DEFAULT_STARTING_POS = 0;
    private static final int DEFAULT_TURTLE_WIDTH = 30;
    private static final int DEFAULT_TURTLE_HEIGHT = 30;

    private boolean active;
    private boolean visible;
    private ImageView myView;
    private double xPos;
    private double yPos;
    private double myHeading;
    private double myDistanceTraveled;
    private SimpleStringProperty name;
    private SimpleStringProperty xPosition;
    private SimpleStringProperty yPosition;

    public TurtleView(){
        myView = new ImageView();
        setView(DEFAULT_STARTING_TURTLE);
        xPos = DEFAULT_STARTING_POS;
        yPos = DEFAULT_STARTING_POS;
        myView.setFitHeight(DEFAULT_TURTLE_HEIGHT);
        myView.setFitWidth(DEFAULT_TURTLE_WIDTH);
        active = false;
    }

    public void setView(String value){
        Image turtleImage = new Image(this.getClass().getResourceAsStream(value));
        myView.setImage(turtleImage);
    }

    public ImageView getView(){
        return myView;
    }

    public double getX(){
        return xPos;
    }

    public double getY(){
        return yPos;
    }

    public double rotate(double angle){
        myView.setRotate(angle);
        setHeading(angle);
        return angle;
    }

    public double getDefaultX(){
        return DEFAULT_STARTING_POS;
    }
    public double getDefaultY(){
        return DEFAULT_STARTING_POS;
    }

    //needed for turtle command "Home"
    public double getMyDistanceTraveled(){
        return myDistanceTraveled;
    }


    public void moveBy(double x, double y){
        xPos += x;
        yPos += y;
        myView.setX(myView.getX() + x);
        myView.setY(myView.getY() + y);
    }

    public Boolean isVisible(){
        return visible;
    }

    public double setHeading(double newHeading){
        double difference = myHeading-newHeading;
        myHeading = newHeading;
        return difference;
    }

    public double getHeading(){
        return myHeading;
    }

    public double getDefaultTurtleWidth(){
        return DEFAULT_TURTLE_WIDTH;
    }
    public double getDefaultTurtleHeight(){
        return DEFAULT_TURTLE_HEIGHT;
    }

    public void setVisibility(boolean vis){
        visible = vis;
    }

    public double setNewCoordinates(double x, double y) {
        xPos = x;
        yPos = y;
        myDistanceTraveled = Math.sqrt(Math.pow(xPos, 2) + Math.pow(yPos, 2));
        return myDistanceTraveled;
    }

    public void deactivate(){
        active = false;
    }

    public void activate(){
        active = true;
    }

    public boolean isActive(){
        return active;
    }
}
