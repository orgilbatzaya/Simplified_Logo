package view;

import javafx.animation.SequentialTransition;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import view.fields.DurationField;


import java.util.HashMap;
import java.util.Map;

/**
 * @author Orgil Batzaya
 */

public class TurtleDisplay extends StackPane {
    private static final int FRAMES_PER_SECOND = 60;
    private static final double GRAPHICS_CONTENT_WIDTH = 10;
    private static final Color PEN_COLOR = Color.RED;
    private static final double MOUSE_SIZE = 10;
    private static final int NUM_STARTING_TURTLES = 3;

    //parameters for display actions
    private static final Color[] COLORS = {Color.GRAY,Color.PURPLE, Color.AZURE,Color.BEIGE,Color.BLUE,Color.VIOLET, Color.GREEN,Color.PALEGOLDENROD};

    private static final String[] SHAPES = {"/images/turtle2.jpg","/images/turtle-basic.png"};

    private Canvas myCanvas;
    private GraphicsContext myGC;
    private SLogoPen myPen;
    private Map<Integer,TurtleView> myTurtles;
    private TurtleView myTurtle;
    private TurtleView myCurrentTurtle;
    private Point2D myPos;
    private SequentialTransition myCurrentAnimation;
    private Rectangle myBackground;
    private Point2D zeroPos;
    private DurationField myDuration;
    private double returnValue;
    private VBox myBox; //May or may not use
    private Pane displayPane;

    //private StatusView statusView;

    public TurtleDisplay(double width, double height) {
        myDuration = new DurationField("Duration of Animation: ");
        myBackground = new Rectangle(width, height);
        myBackground.setFill(Color.WHITE);
        myCanvas = new Canvas(width, height);
        zeroPos = new Point2D(myCanvas.getWidth() / 2, myCanvas.getHeight() / 2);
        myPen = new SLogoPen(PEN_COLOR);
        myGC = myCanvas.getGraphicsContext2D();
        myGC.setLineWidth(GRAPHICS_CONTENT_WIDTH);
        myCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, handler);
        myTurtles = new HashMap<>();
        myTurtle = new TurtleView();
        myBackground.setLayoutX(200);
        myBackground.setY(200);
        this.getChildren().add(myBackground);
        this.getChildren().add(myCanvas);
        displayPane = new Pane();
        //myTurtle.getView().setX(zeroPos.getX());
        //myTurtle.getView().setY(zeroPos.getY());

        makeTurtles(displayPane);
        this.getChildren().add(displayPane);


//        Animate animate = new Animate(myCanvas, myGC, myPen, Duration.seconds(myDuration.getDuration()), myTurtle);
//        myCurrentAnimation = new SequentialTransition(animate.move(new Point2D(50,70)),
//                                                            animate.move(new Point2D(50,30)),
//                                                            animate.move(new Point2D(100,-100)),
//                                                            animate.move(new Point2D(-40,60)),
//                                                            animate.move(new Point2D(-60,200)));
//
//        myCurrentAnimation.setCycleCount(2);
//        myCurrentAnimation.setAutoReverse(true);
//        myCurrentAnimation.play();


    }

    public Canvas getCanvas() {
        return myCanvas;
    }

    public VBox getDurationDisplay() {
        return myDuration.getDisplay();
    }


    EventHandler<MouseEvent> handler = new EventHandler<>() {
        public void handle(MouseEvent e) {
            double size = MOUSE_SIZE;
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;
            myGC.setFill(myPen.getPenColor());
            myGC.setEffect(new DropShadow());
            myGC.fillOval(x, y, size, size);
        }
    };

    private void makeTurtles(Pane displayPane){
        for(int i = 0; i < NUM_STARTING_TURTLES; i++){
            TurtleView t = new TurtleView();
            t.getView().setX(zeroPos.getX() + i*30);
            t.getView().setY(zeroPos.getY());
            t.setNewCoordinates(0 + i*30,0);
            displayPane.getChildren().add(t.getView());
            myTurtles.put(i,t);
            System.out.println(t.getX());
        }
    }

    public Map<Integer, TurtleView> getTurtles(){
        return myTurtles;
    }

    private void addTurtle(){

    }


    public GraphicsContext getGraphicsContext() {
        return myGC;
    }

    public void setBgColor(Color c) {
        myBackground.setFill(c);
    }


    public SequentialTransition getCurrentAnimation() {
        return myCurrentAnimation;
    }

    public TurtleView getMyTurtle() {
        return myTurtle;
    }

    public void clearScreen() {
        myGC.clearRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());
        setToNewPosition( 0, 0);
        myTurtle.getView().setRotate(0);
    }

    public void setToNewPosition(double x, double y) {
        returnValue = myTurtle.setNewCoordinates(x, y);
        myTurtle.getView().setX(zeroPos.getX() + x);
        myTurtle.getView().setY(zeroPos.getY() + y);
        if(x == 0 && y == 0) {
            myTurtle.getView().setRotate(0);
        }
    }

    public void createNewAnimation(Point2D next, TurtleView t) {
        Animate animation = new Animate(myCanvas, myGC, myPen, Duration.seconds(myDuration.getDuration()), t);
        System.out.println(next.getX());
        System.out.println(next.getY());
        myCurrentAnimation = new SequentialTransition(animation.move(next));
        myCurrentAnimation.play();
        
    }

    public void updatePen(double bool) {
        if((!myPen.isVisible() && bool == 1) || (myPen.isVisible() && bool == 0)) {
            myPen.changePenVisibilty();
        }
        returnValue = bool;
        System.out.println(returnValue);
    }

    public SLogoPen getPen() {
        return myPen;
    }

    public Pane getDisplayPane() {
        return displayPane;
    }

    public void setBackgroundColorAction(int index){
        myBackground.setFill(COLORS[index]);
    }

    public void setPenColorAction(int index){
        myPen.setPenColor(COLORS[index]);
    }

    public void setPenWidthAction(double width){
        myPen.setPenWidth(width);
    }

    public void setShapeAction(int index){
        myTurtle.setView(SHAPES[index]);
    }

    public void setPalette(int r, int g, int b, int index){
        Color newColor = Color.rgb(r, g, b); // Color white
        COLORS[index] = newColor;
    }
}