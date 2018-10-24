package view;

import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class TurtleDisplay extends StackPane{
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Canvas myCanvas;
    private GraphicsContext myGC;
    private Color prevPenColor;
    private Color penColor;
    private Color bgColor;
    private TurtleView myTurtle;
    private Point2D myPos;
    private ParallelTransition myCurrentAnimation;
    private Rectangle myBackground;


    public TurtleDisplay() {
        myBackground = new Rectangle(400, 400);
        myBackground.setFill(Color.WHITE);
        myCanvas = new Canvas(400,400);
        myGC = myCanvas.getGraphicsContext2D();
        myGC.setLineWidth(10);
        myCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,handler);
        penColor = Color.RED;
        prevPenColor = Color.RED;
        bgColor = Color.WHITE;
        myTurtle = new TurtleView();
        myTurtle.getView().setVisible(true);
        this.getChildren().add(myBackground);
        this.getChildren().add(myCanvas);
        this.getChildren().add(myTurtle.getView());

        SequentialTransition seq = new SequentialTransition(move(new Point2D(50,70)),
                move(new Point2D(50,-40)),
                move(new Point2D(50,0)));
        seq.play();


    }

    public Canvas getCanvas(){
        return myCanvas;
    }

    public Animation move(Point2D translate){
        //myPos = new Point2D(myTurtle.getX(), myTurtle.getY());
        Location myPos = new Location();

        myPos.x = myTurtle.getX();
        myPos.y = myTurtle.getY();
        //Point2D next = new Point2D(myPos.getX() + translate.getX(), myPos.getX() + translate.getY() );
        Location next = new Location();
        next.x = myPos.x + translate.getX();
        next.y = myPos.y + translate.getY();


        PathTransition drawLine = animate(myPos, next, Duration.seconds(2));

        TranslateTransition translateTurt = new TranslateTransition(Duration.millis(2000), myTurtle.getView());

        translateTurt.setByX(translate.getX());
        translateTurt.setByY(translate.getY());

        myCurrentAnimation = new ParallelTransition(drawLine,translateTurt);
        myTurtle.moveBy((int) translate.getX(), (int) translate.getY());
        System.out.println(myTurtle.getView().getY());

        return myCurrentAnimation;
    }




    EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {


        public void handle(MouseEvent e) {
            double size = 10.0;
            double x = e.getX() - size/2;
            double y = e.getY() - size/2;
            myGC.setFill(penColor);
            myGC.setEffect(new DropShadow());
            myGC.fillOval(x,y,size,size);
        }
    };


    public GraphicsContext getGraphicsContext(){
        return myGC;
    }

    public void setPenColor(Color c){
        penColor = c;
        prevPenColor = c;
    }
    public void setBgColor(Color c){
        myBackground.setFill(c);
    }

    public PathTransition animate(Location current, Location next, Duration duration)
    {
        Path myPath = new Path();
        MoveTo initialPosition = new MoveTo(current.x, current.y);
        LineTo lineTo = new LineTo(next.x,next.y);

        myPath.getElements().add(initialPosition);
        myPath.getElements().add(lineTo);

        Circle pen = new Circle(0, 0, 3);

        PathTransition pathTransition = new PathTransition(duration, myPath, pen);
        pathTransition.currentTimeProperty().addListener(new ChangeListener<Duration>()
        {

            Location oldLocation = null;

            /**
             * Draw a line from the old location to the new location
             */
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {

                // skip starting at 0/0
                if( oldValue == Duration.ZERO)
                    return;

                // get current location
                double x = pen.getTranslateX();
                double y = pen.getTranslateY();

                // initialize the location
                if( oldLocation == null) {
                    oldLocation = new Location();//new Point2D(x+myCanvas.getWidth() / 2, y+myCanvas.getHeight()/2);
                    oldLocation.x = x + myCanvas.getWidth()/2;
                    oldLocation.y = y + myCanvas.getHeight()/2;
                    return;
                }

                // draw line
                myGC.setStroke(penColor);
                myGC.setLineWidth(2);
                myGC.strokeLine(oldLocation.x, oldLocation.y, x+ myCanvas.getWidth() / 2, y+myCanvas.getHeight()/2);


                // update old location with current one
                //oldLocation = new Point2D(x+ myCanvas.getWidth() / 2, y+myCanvas.getHeight()/2);
                oldLocation.x = x +  myCanvas.getWidth() / 2;
                oldLocation.y = y +  myCanvas.getWidth() / 2;
            }
        });

        return pathTransition;
    }

    public ParallelTransition getCurrentAnimation() {
        return myCurrentAnimation;
    }

    public TurtleView getMyTurtle() {
        return myTurtle;
    }

    public void clearScreen(){
        myGC.clearRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());

    }

    public void showPen(){
        penColor = prevPenColor;
    }

    public void hidePen(){
        penColor = (Color) myBackground.getFill();
    }

    public static class Location {
        double x;
        double y;
    }




}

