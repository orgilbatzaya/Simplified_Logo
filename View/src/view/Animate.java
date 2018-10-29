package view;

import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 * @author Orgil Batzaya
 * Enables an animated path to be drawn each time a turtle moves.
 * Credit to "Roland" for the implementation behind pathAnimate
 * https://stackoverflow.com/questions/35585035/what-the-easiest-way-to-animate-a-path-as-an-object-traverses-it
 */

public class Animate {
    private static final int TWO = 2;
    private static final int PEN_RAD = 3;
    private static final int ZERO = 0;


    private Point2D zeroPos;
    private GraphicsContext myGC;
    private PathTransition myPathTans;
    private ParallelTransition myAnimation;

    private SLogoPen myPen;
    private Canvas myCanvas;

    private TurtleView myTurtle;
    private Point2D myPos;
    private Duration duration;



    public Animate(Canvas myCanvas, GraphicsContext gc, SLogoPen pen, Duration duration, TurtleView turtle){

        myTurtle = turtle;
        zeroPos = new Point2D(myCanvas.getWidth() /TWO, myCanvas.getHeight() / TWO);
        this.duration = duration;
        this.myCanvas = myCanvas;
        myGC = gc;
        myPen = pen;

    }

    public ParallelTransition getAnimation(){
        return myAnimation;
    }

    public ParallelTransition move(Point2D translate){
        myPos = new Point2D(myTurtle.getX(),myTurtle.getY());
        Point2D next = myPos.add(translate);
        pathAnimate(myPos,next,duration);

        myAnimation = new ParallelTransition(myPathTans);
        myTurtle.moveBy((int) translate.getX(), (int) translate.getY());

        return myAnimation;
    }

    public void pathAnimate(Point2D current, Point2D next, Duration duration) {
        Path myPath = new Path();
        MoveTo initialPosition = new MoveTo(current.getX(), current.getY());
        LineTo lineTo = new LineTo(next.getX(), next.getY());

        myPath.getElements().add(initialPosition);
        myPath.getElements().add(lineTo);

        Circle pen = new Circle(ZERO, ZERO, PEN_RAD);

        myPathTans = new PathTransition(duration, myPath, pen);
        myPathTans.currentTimeProperty().addListener(new ChangeListener<>() {

            Point2D oldLocation = null;

            //Draw a line from the old location to the new location
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {

                // skip starting at 0/0
                if (oldValue == Duration.ZERO)
                    return;

                // get current location
                Point2D moveVector = new Point2D(pen.getTranslateX(), pen.getTranslateY());

                Point2D newLocation = zeroPos.add(moveVector);
                // initialize the location
                if (oldLocation == null) {

                    oldLocation = newLocation;
                    return;
                }

                // draw line
                if(myPen.isVisible()) {
                    myGC.setStroke(myPen.getPenColor());
                    myGC.setLineWidth(myPen.getWidth());
                    myGC.strokeLine(oldLocation.getX(), oldLocation.getY(), newLocation.getX(), newLocation.getY());
                }

                // update old location with current one
                oldLocation = newLocation;
            }
        });
    }

}
