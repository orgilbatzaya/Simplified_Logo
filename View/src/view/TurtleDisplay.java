package view;

import javafx.animation.SequentialTransition;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
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

public class TurtleDisplay extends StackPane {
    private static final int FRAMES_PER_SECOND = 60;
    private static final double GRAPHICS_CONTENT_WIDTH = 10;
    private static final Color PEN_COLOR = Color.RED;
    private static final Color PREVIOUS_PEN_COLOR = Color.RED;
    private static final double MOUSE_SIZE = 10;

    private Canvas myCanvas;
    private GraphicsContext myGC;
    private Color prevPenColor;
    private Color penColor;
    private TurtleView myTurtle;
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
        myGC = myCanvas.getGraphicsContext2D();
        myGC.setLineWidth(GRAPHICS_CONTENT_WIDTH);
        myCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, handler);
        penColor = PEN_COLOR;
        prevPenColor = PREVIOUS_PEN_COLOR;
        myTurtle = new TurtleView();
        myBackground.setLayoutX(200);
        myBackground.setY(200);
        this.getChildren().add(myBackground);
        this.getChildren().add(myCanvas);
        displayPane = new Pane(myTurtle.getView());
        myTurtle.getView().setX(zeroPos.getX());
        myTurtle.getView().setY(zeroPos.getY());
        this.getChildren().add(displayPane);


        //Animate animate = new Animate(myCanvas, myGC, penColor, Duration.seconds(myDuration.getDuration()), myTurtle);
        /*myCurrentAnimation = new SequentialTransition(animate.move(new Point2D(50,70)),
                                                            animate.move(new Point2D(50,30)),
                                                            animate.move(new Point2D(100,-100)),
                                                            animate.move(new Point2D(-40,60)),
                                                            animate.move(new Point2D(-60,200)));

        myCurrentAnimation.setCycleCount(2);
        myCurrentAnimation.setAutoReverse(true);
        myCurrentAnimation.play();
        */

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
            myGC.setFill(penColor);
            myGC.setEffect(new DropShadow());
            myGC.fillOval(x, y, size, size);
        }
    };


    public GraphicsContext getGraphicsContext() {
        return myGC;
    }

    public void setPenColor(Color c) {
        penColor = c;
        prevPenColor = c;
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
    }

    public void showPen() {
        penColor = prevPenColor;
    }

    public void hidePen() {
        penColor = (Color) myBackground.getFill();
    }

    public void resetToHomePosition() {
        returnValue = myTurtle.setNewCoordinates(0, 0);
        myTurtle.getView().setX(zeroPos.getX());
        myTurtle.getView().setY(zeroPos.getY());
    }

    public void setToNewPosition(double x, double y) {
        returnValue = myTurtle.setNewCoordinates(x, y);
        myTurtle.getView().setX(zeroPos.getX() + x);
        myTurtle.getView().setY(zeroPos.getY() + y);
    }

    public void createNewAnimation(Point2D next) {
        Animate animation = new Animate(myCanvas, myGC, penColor, Duration.seconds(myDuration.getDuration()), myTurtle);
        myCurrentAnimation = new SequentialTransition(animation.move(next));
        myCurrentAnimation.play();
        returnValue = myTurtle.setNewCoordinates(next.getX(), next.getY());
    }

    public void getReturnValue() {
        System.out.println(returnValue);
    }
}