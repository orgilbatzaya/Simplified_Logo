package view;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;


public class TurtleDisplay extends StackPane{
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Canvas myCanvas;
    private GraphicsContext myGC;
    private Color penColor;
    private Color bgColor;
    private TurtleView myTurtle;
    private Point2D myPos;

    public TurtleDisplay(){
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
        Circle pen = new Circle(0, 0, 3);
        myPos = new Point2D(50, 340);
        //var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        //var myAnimation = new Timeline();
        //myAnimation.setCycleCount(Timeline.INDEFINITE);
        //myAnimation.getKeyFrames().add(frame);
        //myAnimation.play();
        Animation animation1 = move(myTurtle.getView(), Duration.seconds(10));
        //Animation animation2 = move(, Duration.millis((3000)));
        //ParallelTransition parallelTransition = new ParallelTransition(animation1, animation2);
        //parallelTransition.play();
        animation1.play();
        System.out.println(myTurtle.getX());
        System.out.println(myTurtle.getY());
    }

    public Canvas getCanvas(){
        return myCanvas;
    }

    public EventHandler<MouseEvent> handler = new EventHandler<>() {

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

    public Animation move(ImageView v, Duration duration)
    {
        Path myPath = new Path();
        MoveTo initialPosition = new MoveTo(v.getX(), v.getY());
        LineTo lineTo = new LineTo();
        lineTo.setX(v.getX()+200);
        lineTo.setY(v.getY());
        myPath.getElements().add(initialPosition);
        myPath.getElements().add(lineTo);

        Circle pen = new Circle(0, 0, 3);

        // create path transition
        PathTransition pathTransition = new PathTransition(duration, myPath, pen);
        pathTransition.currentTimeProperty().addListener(new ChangeListener<Duration>()
        {

            Point2D oldLocation = null;

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
                    oldLocation = new Point2D(x+myCanvas.getWidth() / 2, y+myCanvas.getHeight()/2);
                    return;
                }

                // draw line
                myGC.setStroke(Color.BLUE);
                myGC.setFill(Color.YELLOW);
                myGC.setLineWidth(4);
                myGC.strokeLine(oldLocation.getX(), oldLocation.getY(), x+ myCanvas.getWidth() / 2, y+myCanvas.getHeight()/2);

                // update old location with current one
                oldLocation = new Point2D(x+ myCanvas.getWidth() / 2, y+myCanvas.getHeight()/2);
            }
        });

        return pathTransition;
    }
}
