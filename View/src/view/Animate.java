package view;

import javafx.animation.PathTransition;
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
    private Color myPenColor;

    public Animate(Canvas myCanvas, Color penColor, Point2D current, Point2D next, Duration duration){
        zeroPos = new Point2D(myCanvas.getWidth() /2, myCanvas.getHeight() / 2);
        myGC = myCanvas.getGraphicsContext2D();
        myPenColor = penColor;
        animate(current,next,duration);
    }

    public PathTransition getAnimation(){
        return myPathTans;
    }

    public void animate(Point2D current, Point2D next, Duration duration) {
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
                //double x = pen.getTranslateX();
                //double y = pen.getTranslateY();

                // initialize the location
                if (oldLocation == null) {

                    oldLocation = new Point2D(moveVector.getX() + zeroPos.getX(), moveVector.getY() + zeroPos.getY());
                    return;
                }
                Point2D newLocation = new Point2D(moveVector.getX() + zeroPos.getX(), moveVector.getY() + zeroPos.getY());
                // draw line
                myGC.setStroke(myPenColor);
                myGC.setLineWidth(2);
                myGC.strokeLine(oldLocation.getX(), oldLocation.getY(), newLocation.getX(), newLocation.getY());

                // update old location with current one
                oldLocation = newLocation;
            }
        });
    }
        //return pathTransition;

}
