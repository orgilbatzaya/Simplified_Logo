package View;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class TurtleDisplay {
    private Canvas myCanvas;
    private Label myLabel;
    private int[] myDim = {40,40};


    public TurtleDisplay(){
        myCanvas = new Canvas(400,400);
        GraphicsContext gc = myCanvas.getGraphicsContext2D();
        initDraw(gc);

    }

    public Canvas getCanvas(){
        return myCanvas;
    }

    

    private void initDraw(GraphicsContext gc){
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();

        gc.setFill(Color.WHITE);

        gc.fillRect(50, 50, canvasWidth, canvasHeight);

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(10);

    }



}
