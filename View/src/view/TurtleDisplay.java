package view;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;


public class TurtleDisplay extends StackPane{
    private Canvas myCanvas;
    private GraphicsContext myGC;
    private Color penColor;
    private Color bgColor;
    private TurtleView myTurtle;


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



    }

    public Canvas getCanvas(){
        return myCanvas;
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

    public TurtleView getMyTurtle(){
        return myTurtle;
    }



}
