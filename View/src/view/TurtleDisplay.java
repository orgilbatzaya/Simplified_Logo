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
import javafx.scene.input.PickResult;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;
import view.fields.DurationField;

public class TurtleDisplay extends StackPane{
    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Color prevPenColor;

    private TurtleView myTurtle;
    private SequentialTransition myCurrentAnimation;
    private Rectangle myBackground;
    private DurationField myDuration;
    private SLogoCanvas myCanvas;


    public TurtleDisplay() {
        myCanvas = new SLogoCanvas(400, 400);
        myDuration = new DurationField("Duration of Animation: ");
        myBackground = new Rectangle(400, 400);
        myBackground.setFill(Color.WHITE);
        prevPenColor = Color.RED;
        myTurtle = new TurtleView();
        myTurtle.getView().setVisible(true);
        this.getChildren().add(myBackground);
        this.getChildren().add(myCanvas.getCanvas());
        this.getChildren().add(myTurtle.getView());
        Animate animate = new Animate(myCanvas,myCanvas.getPenColor(),Duration.seconds(myDuration.getDuration()),myTurtle);
        myCurrentAnimation = new SequentialTransition(animate.move(new Point2D(50,70)),
                                                            animate.move(new Point2D(50,30)),
                                                            animate.move(new Point2D(100,-100)),
                                                            animate.move(new Point2D(-40,60)));
        myCurrentAnimation.setCycleCount(2);
        myCurrentAnimation.setAutoReverse(true);
        myCurrentAnimation.play();
    }

    public SLogoCanvas getCanvas(){
        return myCanvas;
    }

    public VBox getDurationDisplay() {
        return myDuration.getDisplay();
    }

    public void setBgColor(Color c){
        myBackground.setFill(c);
    }



    public SequentialTransition getCurrentAnimation() {
        return myCurrentAnimation;
    }

    public TurtleView getMyTurtle() {
        return myTurtle;
    }

    public void resetToHomePosition() {
        myTurtle.setX(myCanvas.getHomeX());
        myTurtle.setY(myCanvas.getHomeY());
    }
}

