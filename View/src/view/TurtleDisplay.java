package view;

import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import view.environmentdisplays.StatusDisplay;
import view.fields.DurationField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Orgil Batzaya, Austin Kao
 */

public class TurtleDisplay extends StackPane implements FrontExternal, ViewResourceBundles{
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
    private StatusDisplay currentInfo;

    private static final double DEFAULT_PEN = 1;
    private static final double DEFAULT_VISIBLE = 1;
    private static final double INITIAL_DISTANCE_MOVED = 0;

    private static final double INFO_LAYOUT_X = 800;
    private static final double INFO_LAYOUT_Y = 50;
    private static final double TURTLE_INFO_HEIGHT = 100;
    private static final String TURTLE_INFO_LABEL = "turtleInfo";


    public TurtleDisplay(double width, double height) {
        initializeCanvas(width,height);
        myDuration = new DurationField(myDefaults.getString(DURATION_LABEL));
        zeroPos = new Point2D(midPoint(0, myCanvas.getWidth()), midPoint(0, myCanvas.getHeight()));
        myPen = new SLogoPen(PEN_COLOR);
        myTurtles = new HashMap<>();
        myTurtle = new TurtleView(0);
        displayPane = new Pane();
        this.getChildren().add(displayPane);
        makeTurtles();
        colorMap = new HashMap<>();
        myCurrentAnimation = new SequentialTransition();
        createTurtleInfo();
    }

    private void initializeCanvas(double width, double height) {
        myBackground = new Rectangle(width, height);
        myBackground.setFill(Color.WHITE);
        myCanvas = new Canvas(width, height);
        myGC = myCanvas.getGraphicsContext2D();
        myGC.setLineWidth(GRAPHICS_CONTENT_WIDTH);
        this.getChildren().add(myBackground);
        this.getChildren().add(myCanvas);
    }

    public Canvas getCanvas() {
        return myCanvas;
    }

    public VBox getDurationDisplay() {
        return myDuration.getDisplay();
    }


    private void makeTurtles(){
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

    private void addTurtle(int index){
        myTurtles.put(index,new TurtleView(index));
    }

    public TurtleView getMyTurtle() {
        return myTurtle;
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


    public void clearScreen(TurtleView t) {
        myGC.clearRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());
        setToNewPosition( 0, 0,t);
    }

    public void setToNewPosition(double x, double y, TurtleView t) {
        t.getView().setX(zeroPos.getX() + x);
        t.getView().setY(zeroPos.getY() + y);
        if(x == 0 && y == 0) {
            t.getView().setRotate(0);
            t.getView().setX(zeroPos.getX() + x - midPoint(0, t.getView().getFitWidth()));
            t.getView().setY(zeroPos.getY() + y - midPoint(0, t.getView().getFitHeight()));
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

    public void deactivateAllTurtles(){
        for(int i = 0; i<myTurtles.size();i++){
            myTurtles.get(i).deactivate();
        }
    }
    public List<Map<String,Double>> getTurtleParams(){
        List<Map<String,Double>> outList = new ArrayList<>();
        for(int i = 0; i<this.getTurtles().size(); i++) {
            outList.add(new HashMap<String,Double>());
            Double[] valueElements = {this.getTurtles().get(i).getHeading(),
                    this.getTurtles().get(i).getX(),
                    this.getTurtles().get(i).getY(),
                    DEFAULT_PEN, DEFAULT_VISIBLE, INITIAL_DISTANCE_MOVED,
                    (double) this.getTurtles().get(i).getMyID(),
                    (double) (this.getTurtles().get(i).isActive()?1:0)};
            for (int j = 0; j < keyElements.length; j++) {
                outList.get(i).put(keyElements[j], valueElements[j]);
            }
        }
        return outList;
    }

    public void createTurtleInfo(){
        currentInfo = new StatusDisplay(TURTLE_INFO_HEIGHT, getDefault(TURTLE_INFO_LABEL), this);
        currentInfo.getDisplay().setLayoutX(INFO_LAYOUT_X);
        currentInfo.getDisplay().setLayoutY(INFO_LAYOUT_Y);
    }
    public StatusDisplay getTurtleInfoDisplay() {
        return currentInfo;
    }
}