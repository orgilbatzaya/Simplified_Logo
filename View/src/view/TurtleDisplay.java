package view;

import javafx.animation.Animation;
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
 * @author Orgil Batzaya, Austin Kao
 */

public class TurtleDisplay extends StackPane implements ViewResourceBundles{
    private static final double GRAPHICS_CONTENT_WIDTH = 10;
    private static final Color PEN_COLOR = Color.RED;
    private static final double MOUSE_SIZE = 10;
    private static final int NUM_STARTING_TURTLES = 3;
    private static final String DURATION_LABEL = "duration";

    private Canvas myCanvas;
    private GraphicsContext myGC;
    private SLogoPen myPen;
    private Map<Integer,TurtleView> myTurtles;
    private SequentialTransition myCurrentAnimation;
    private Rectangle myBackground;
    private Point2D zeroPos;
    private DurationField myDuration;
    private Pane displayPane;
    private Map<Integer, Color> colorMap;
    private TurtleView myTurtle;


    public TurtleDisplay(double width, double height) {
        myDuration = new DurationField(myDefaults.getString(DURATION_LABEL));
        myBackground = new Rectangle(width, height);
        myBackground.setFill(Color.WHITE);
        myCanvas = new Canvas(width, height);
        zeroPos = new Point2D(midPoint(0, myCanvas.getWidth()), midPoint(0, myCanvas.getHeight()));
        myPen = new SLogoPen(PEN_COLOR);
        myGC = myCanvas.getGraphicsContext2D();
        myGC.setLineWidth(GRAPHICS_CONTENT_WIDTH);
        //myCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, handler);
        myTurtles = new HashMap<>();
        myTurtle = new TurtleView(0);
        this.getChildren().add(myBackground);
        this.getChildren().add(myCanvas);
        displayPane = new Pane();
        myCurrentAnimation = new SequentialTransition();
        this.getChildren().add(displayPane);
        makeTurtles(displayPane);
        colorMap = new HashMap<>();
    }

    public Canvas getCanvas() {
        return myCanvas;
    }

    public VBox getDurationDisplay() {
        return myDuration.getDisplay();
    }


//    EventHandler<MouseEvent> handler = new EventHandler<>() {
//        public void handle(MouseEvent e) {
//            double size = MOUSE_SIZE;
//            double x = e.getX() - midPoint(0, size);
//            double y = e.getY() - midPoint(0, size);
//            myGC.setFill(myPen.getPenColor());
//            myGC.setEffect(new DropShadow());
//            myGC.fillOval(x, y, size, size);
//        }
//    };


    private void makeTurtles(Pane displayPane){
        for(int i = 0; i < NUM_STARTING_TURTLES; i++){
            TurtleView t = new TurtleView(i);
            t.getView().setX(zeroPos.getX() + i*30 - midPoint(0, t.getView().getFitWidth()));
            t.getView().setY(zeroPos.getY() - midPoint(0, t.getView().getFitHeight()));
            t.setNewCoordinates(0 + i*30,0);
            displayPane.getChildren().add(t.getView());
            myTurtles.put(i,t);
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

    public void setBgColor(Integer index) {
        Color c = determineColor(index);
        setBgColor(c);
    }


    public Animation getCurrentAnimation() {
        return myCurrentAnimation;
    }

    public TurtleView getMyTurtle() {
        return myTurtle;
    }

    public void clearScreen(TurtleView t) {
        myGC.clearRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());
        setToNewPosition( 0, 0,t);
        myTurtle.getView().setRotate(0);
    }

    public void setToNewPosition(double x, double y, TurtleView t) {
        t.getView().setX(zeroPos.getX() + x - midPoint(0, myTurtle.getView().getFitWidth()));
        t.getView().setY(zeroPos.getY() + y - midPoint(0, myTurtle.getView().getFitHeight()));
        if(x == 0 && y == 0) {
            myTurtle.getView().setRotate(0);
        }
    }

    public void createNewAnimation(Point2D next, TurtleView t) {
        Animate animation = new Animate(myCanvas, myGC, myPen, Duration.seconds(myDuration.getDuration()), t);
        myCurrentAnimation.getChildren().add(animation.move(next));
        myCurrentAnimation.playFromStart();
        myCurrentAnimation.setOnFinished(e -> resetAnimation());
    }

    public void updatePen(double bool) {
        if((!myPen.isVisible() && bool == 1) || (myPen.isVisible() && bool == 0)) {
            myPen.changePenVisibilty();
        }
    }

    public SLogoPen getPen() {
        return myPen;
    }

    public Pane getDisplayPane() {
        return displayPane;
    }

    public void resetAnimation() {
        myCurrentAnimation.getChildren().clear();
    }

    public void changeTurtleImage(int i) {
        for(TurtleView turtle : myTurtles.values()) {
            turtle.setView(myImages.getString(Integer.toString(i)));
        }
    }

    public void changePenColor(Integer index) {
        Color c = determineColor(index);
        myPen.setPenColor(c);
    }

    public void makeNewColor(int index, int r, int g, int b) {
        Color c = Color.rgb(r, g, b);
        colorMap.put(index, c);
    }

    private Color determineColor(Integer index) {
        Color c;
        if(colorMap.containsKey(index)) {
            c = colorMap.get(index);
        } else {
            c = Color.valueOf(myColors.getString(index.toString()));
        }
        return c;
    }

    private double midPoint(double a, double b) {
        return (a + b)/2;
    }
}