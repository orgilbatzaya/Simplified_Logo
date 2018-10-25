package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class TurtleView {
    private static final String DEFAULT_STARTING_TURTLE = "/images/turtle-basic.png";
    private static final int DEFAULT_STARTING_POS = 0;
    private static final int DEFAULT_TURTLE_WIDTH = 30;
    private static final int DEFAULT_TURTLE_HEIGHT = 30;

    private double mySpeed;
    private Color penColor;
    private boolean penDown;
    private double penWidth;
    private boolean visible;
    private ImageView myView;
    private double xPos;
    private double yPos;
    private double myHeading;
    private double myDistanceTraveled;



    public TurtleView(){
        myView = new ImageView();
        setView(DEFAULT_STARTING_TURTLE);
        xPos = DEFAULT_STARTING_POS;
        yPos = DEFAULT_STARTING_POS;
        myView.setX(xPos);
        myView.setY(yPos);
        myView.setFitHeight(DEFAULT_TURTLE_HEIGHT);
        myView.setFitWidth(DEFAULT_TURTLE_WIDTH);

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

    public void setX(double x){
        xPos = x;
    }
    
    public void setY(double y){
        yPos = y;
    }

    public void setXTurtle(double x){
        setX(x);
        myView.setX(x);
    }
    public void setYTurtle(double y){
        setY(y);
        myView.setX(y);
    }

    public void move(double distance){
        double changeX = distance*Math.cos(Math.toRadians(myHeading));
        double changeY = distance*Math.sin(Math.toRadians(myHeading));
        setXTurtle(myView.getX()+changeX);
        setYTurtle(myView.getY()+changeY);
        myDistanceTraveled = distance+myDistanceTraveled;
    }

    public void rotate(double angle){
        myView.setRotate(angle);
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

    public void moveBy(int x, int y){
        xPos += x;
        yPos += y;
        myView.setX(xPos);
        myView.setY(yPos);

    }

    public Boolean getPenDown(){
        return penDown;
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

    public boolean getVisible(){
        return visible;
    }
    public void setVisible(boolean vis){
        visible = vis;
    }
}
