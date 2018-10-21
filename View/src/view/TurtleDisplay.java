package view;

import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.util.Duration;


public class TurtleDisplay extends StackPane{
    private Canvas myCanvas;
    private GraphicsContext myGC;
    private Color penColor;
    private Color bgColor;
    private TurtleView myTurtle;



    public TurtleDisplay(){
        //this.setBackground(new Background(new BackgroundFill(Color.RED)));
        myCanvas = new Canvas(400,400);
        myGC = myCanvas.getGraphicsContext2D();
        initDraw(myGC);
        myCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,handler);
        penColor = Color.RED;
        bgColor = Color.WHITE;
        myTurtle = new TurtleView();

        myTurtle.getView().setVisible(true);
        this.getChildren().add(myCanvas);
        this.getChildren().add(myTurtle.getView());
        this.setAlignment(myTurtle.getView(), Pos.CENTER);
        move(myTurtle.getX(), myTurtle.getY());
        move(myTurtle.getX(),myTurtle.getY());

    }

    public Canvas getCanvas(){
        return myCanvas;
    }

    public void move(int nextX, int nextY){
        Location current = new Location();
        current.x = myTurtle.getX();
        current.y = myTurtle.getY();
        Location next = new Location();
        next.x = nextX+100;
        next.y = nextY+100;

        PathTransition drawLine = animate(current, next, Duration.seconds(3));

        TranslateTransition translateTurt = new TranslateTransition(Duration.millis(3000), myTurtle.getView());
        translateTurt.setByX(next.x);
        translateTurt.setByY(next.x);

        ParallelTransition both = new ParallelTransition(drawLine,translateTurt);
        both.play();
        myTurtle.moveBy((int) next.x, (int) next.y);

        System.out.println(myTurtle.getX());

    }




    EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {

        public void handle(MouseEvent e) {
            double size = 10.0;
            double x = e.getX() - size/2;
            double y = e.getY() - size/2;
            myGC.setFill(penColor);
            myGC.setEffect(new DropShadow());
            myGC.fillRect(x,y,size,size);
        }

    };


    public GraphicsContext getGraphicsContext(){
        return myGC;
    }

    public void setPenColor(Color c){
        penColor = c;
    }
    public void setBgColor(Color c){
        bgColor = c;
        myGC.setFill(bgColor);
        myGC.fillRect(0,0,myCanvas.getWidth(),myCanvas.getHeight());
    }



    private void initDraw(GraphicsContext gc){
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();

        gc.setFill(Color.WHITE);

        gc.fillRect(0, 0, canvasWidth, canvasHeight);

        //gc.setStroke(Color.BLACK);
        gc.setLineWidth(10);

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
                    oldLocation = new Location();
                    oldLocation.x = x+myCanvas.getWidth() / 2;
                    oldLocation.y = y+myCanvas.getHeight()/2;
                    return;
                }

                // draw line
                myGC.setStroke(Color.BLUE);
                myGC.setFill(Color.YELLOW);
                myGC.setLineWidth(4);
                myGC.strokeLine(oldLocation.x, oldLocation.y, x+ myCanvas.getWidth() / 2, y+myCanvas.getHeight()/2);

                // update old location with current one
                oldLocation.x = x+ myCanvas.getWidth() / 2;
                oldLocation.y = y+myCanvas.getHeight()/2;
            }
        });

        return pathTransition;
    }

    public static class Location {
        double x;
        double y;
    }










}
