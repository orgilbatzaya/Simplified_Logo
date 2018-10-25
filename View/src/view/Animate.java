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

public class Animate {
    private Point2D zeroPos;
    private GraphicsContext myGC;
    private PathTransition myPathTans;
    private ParallelTransition myAnimation;
    private Color myPenColor;
    private SLogoCanvas myCanvas;
    private TurtleView myTurtle;
    private Point2D myPos;
    private Duration duration;


    public Animate(SLogoCanvas canvas, Color penColor, Duration duration, TurtleView turtle){
        myTurtle = turtle;
        this.duration = duration;
        myCanvas = canvas;
        myGC = canvas.getGraphicsContext();
        myPenColor = penColor;
        zeroPos = new Point2D(canvas.getHomeX(), canvas.getHomeY());
    }

    public ParallelTransition getAnimation(){
        return myAnimation;
    }

    public ParallelTransition move(Point2D translate){
        myPos = new Point2D(myTurtle.getX(),myTurtle.getY());
        Point2D next = myPos.add(translate);
        pathAnimate(myPos,next,duration);

        TranslateTransition translateTurt = new TranslateTransition(duration, myTurtle.getView());
        translateTurt.setByX(translate.getX());
        translateTurt.setByY(translate.getY());

        myAnimation = new ParallelTransition(myPathTans,translateTurt);
        myTurtle.moveBy((int) translate.getX(), (int) translate.getY());

        return myAnimation;
    }

    public void pathAnimate(Point2D current, Point2D next, Duration duration) {
        Path myPath = new Path();
        MoveTo initialPosition = new MoveTo(current.getX(), current.getY());
        LineTo lineTo = new LineTo(next.getX(), next.getY());

        myPath.getElements().add(initialPosition);
        myPath.getElements().add(lineTo);

        Circle pen = new Circle(0, 0, 3);

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


                // initialize the location
                if (oldLocation == null) {

                    oldLocation = zeroPos.add(moveVector);
                    return;
                }
                Point2D newLocation = zeroPos.add(oldLocation);
                // draw line
                myGC.setStroke(myPenColor);
                myGC.setLineWidth(2);
                myGC.strokeLine(oldLocation.getX(), oldLocation.getY(), newLocation.getX(), newLocation.getY());

                // update old location with current one
                oldLocation = newLocation;
            }
        });
    }

}
