package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class TurtleView{
    private static final String DEFAULT_STARTING_TURTLE = "/resources/images/turtle-basic.png";
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
        Image turtleImage = new Image(this.getClass().getResourceAsStream(DEFAULT_STARTING_TURTLE));
        myView.setImage(turtleImage);
        xPos = DEFAULT_STARTING_POS;
        yPos = DEFAULT_STARTING_POS;
        myView.setX(xPos);
        myView.setY(yPos);
        myView.setFitHeight(DEFAULT_TURTLE_HEIGHT);
        myView.setFitWidth(DEFAULT_TURTLE_WIDTH);

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
        xPos = x;
        myView.setX(x);
    }
    public void setYTurtle(double y){
        yPos = y;
        myView.setX(y);
    }

    public void move(double distance, double angle){
        double angleFinal = myHeading-angle;// is this necessary?
        double changeX = distance*Math.cos(Math.toRadians(angleFinal));
        double changeY = distance*Math.sin(Math.toRadians(angleFinal));
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

    public void setPenDown(Boolean pen){
        penDown = pen;
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