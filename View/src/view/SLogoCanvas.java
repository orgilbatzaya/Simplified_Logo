package view;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SLogoCanvas {
    private Canvas myCanvas;
    private GraphicsContext myGC;
    private Point2D zeroPos;
    private Color penColor;
    private boolean penVisibility;

    public SLogoCanvas(double width, double height) {
        myCanvas = new Canvas(width,height);
        myCanvas.setVisible(true);
        myGC = myCanvas.getGraphicsContext2D();
        myGC.setLineWidth(10);
        myCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,handler);
        zeroPos = new Point2D(myCanvas.getWidth() /2, myCanvas.getHeight() / 2);
        penColor = Color.RED;
    }

    EventHandler<MouseEvent> handler = new EventHandler<>() {
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

    public void clearScreen(){
        myGC.clearRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());
    }

    public Canvas getCanvas() {
        return myCanvas;
    }

    public double getHomeX() {
        return zeroPos.getX();
    }

    public double getHomeY() {
        return zeroPos.getY();
    }

    public Color getPenColor() {
        return penColor;
    }

    public void setPenColor(Color c){
        penColor = c;
    }

    public void changePenVisibilty(){
        penVisibility = !penVisibility;
    }
}
